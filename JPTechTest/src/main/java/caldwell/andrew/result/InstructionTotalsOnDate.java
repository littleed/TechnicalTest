package caldwell.andrew.result;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Holds the instruction totals by date
 * 
 * @author acaldwell
 *
 */
public class InstructionTotalsOnDate {

	private Map<LocalDate, BigDecimal> instructionTotals;

	public InstructionTotalsOnDate() {
		instructionTotals = new HashMap<LocalDate, BigDecimal>();
	}

	/**
	 * 
	 * @param date   The date the instruction took place (taking account any
	 *               required adjustments). Cannot be null.
	 * @param amount The total amount of the instruction is USD. Cannot be null.
	 */
	public void addAmount(LocalDate date, BigDecimal amount) {
		if(date==null||amount==null) {
			throw new IllegalArgumentException("Must provide a date and amount");
		}
		BigDecimal total = BigDecimal.ZERO;
		if (instructionTotals.containsKey(date)) {
			total = instructionTotals.get(date);
		}
		instructionTotals.put(date, total.add(amount));
	}

	/**
	 * 
	 * @param date The date we want the total for
	 * @return The total stored within this object on the specified date
	 */
	public BigDecimal getTotalOnDate(LocalDate date) {
		BigDecimal total = BigDecimal.ZERO;
		if (instructionTotals.containsKey(date)) {
			total = instructionTotals.get(date);
		}
		return total;
	}
}
