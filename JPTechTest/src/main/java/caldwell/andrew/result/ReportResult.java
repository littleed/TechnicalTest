package caldwell.andrew.result;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import caldwell.andrew.Instruction;
import caldwell.andrew.InstructionType;
import caldwell.andrew.settlementdate.SettlementDateCalculator;
import caldwell.andrew.settlementdate.SettlementDateCalculatorFactory;

/**
 * Holds the data for the report
 * 
 * @author acaldwell
 *
 */
public class ReportResult {

	private Map<InstructionType, InstructionTotalsOnDate> totalsByInstructionType;
	private Set<LocalDate> instructionDates;
	private Set<String> entities;
	private Map<InstructionType, EntityRank> entityRanks;
	private SettlementDateCalculatorFactory settlementDateCalculatorFactory;

	public ReportResult() {
		instructionDates = new HashSet<LocalDate>();
		entities = new HashSet<>();
		totalsByInstructionType = new HashMap<InstructionType, InstructionTotalsOnDate>();
		entityRanks = new HashMap<>();
		for (InstructionType type : InstructionType.values()) {
			totalsByInstructionType.put(type, new InstructionTotalsOnDate());
			entityRanks.put(type, new EntityRank());
		}
		settlementDateCalculatorFactory = new SettlementDateCalculatorFactory();
	}

	/**
	 * 
	 * @param instruction An instruction to add to the report. Cannot be null.
	 */
	public void addInstruction(Instruction instruction) {
		if (instruction == null) {
			throw new IllegalArgumentException("Must provide an instruction");
		}
		SettlementDateCalculator settlementDateCalculator = settlementDateCalculatorFactory
				.getSettlementDateCalculator(instruction.getCurrency());
		LocalDate adjustedSettlementDate = settlementDateCalculator.getAdjustedSettlementDate(instruction);
		BigDecimal amount = getAmountToTradeUSD(instruction);
		totalsByInstructionType.get(instruction.getType()).addAmount(adjustedSettlementDate, amount);
		instructionDates.add(adjustedSettlementDate);
		entities.add(instruction.getEntity());
		entityRanks.get(instruction.getType()).addInstruction(instruction.getEntity(), amount);
	}

	/**
	 * 
	 * @return All the adjusted settlement dates added so far
	 */
	public List<LocalDate> getAllDates() {
		return instructionDates.stream().sorted().collect(Collectors.toList());
	}

	/**
	 * 
	 * @return All entities that have been added to this report
	 */
	public Collection<String> getAllEntities() {
		return entities;
	}

	/**
	 * 
	 * @param type Buy or Sell
	 * @return The entities added to the report result added by their highest
	 *         Instruction for the specified instruction type
	 */
	public List<String> getRankedEntities(InstructionType type) {
		return entityRanks.get(type).getRankedEntities();
	}

	/**
	 * 
	 * @param instructionType Either Buy or Sell
	 * @param settlementDate  The settlementDate we want the total for
	 * @return The total amount bought or sold on the specified date, or zero if not
	 *         found
	 */
	public BigDecimal getTotal(InstructionType instructionType, LocalDate settlementDate) {
		InstructionTotalsOnDate instructionTotals = totalsByInstructionType.get(instructionType);
		BigDecimal total = BigDecimal.ZERO;
		if (instructionTotals != null) {
			total = instructionTotals.getTotalOnDate(settlementDate);
		}
		return total;
	}

	/**
	 * 
	 * @param instruction The instruction to calculate the total for. Cannot be
	 *                    null.
	 * @return The total for amount transferred by this instruction
	 */
	BigDecimal getAmountToTradeUSD(Instruction instruction) {
		return instruction.getPricePerUnit().multiply(new BigDecimal(instruction.getUnits()))
				.multiply(instruction.getAgreedFx()).setScale(2, RoundingMode.HALF_EVEN);
	}

}
