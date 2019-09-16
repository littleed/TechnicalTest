package caldwell.andrew.settlementdate;

import java.time.LocalDate;

import caldwell.andrew.Instruction;

/**
 * Interface for defining SettlementDateCalculators. Implementations of this
 * look at the Insutruction provided and return the adjusted settlement date
 * based on any internal riles set
 * 
 * @author acaldwell
 *
 */
public interface SettlementDateCalculator {

	/**
	 * 
	 * @param instruction The instruction
	 * @return The settlement date, which may or may not be the same as a settlement
	 *         date in the instruction, depending on implementation. Will throw an
	 *         Exception if a null Insruction is passed.
	 */
	public LocalDate getAdjustedSettlementDate(Instruction instruction);
}
