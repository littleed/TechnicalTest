package caldwell.andrew.settlementdate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

import caldwell.andrew.Instruction;

/**
 * Default settlementdatecalculator. Will calculate the settlement date based on
 * avoiding any specified days of the week. In the event of the settlement date
 * being on a "nonworkingday", the date returned is incremented until it no
 * longer lies on that date.
 * 
 * @author acaldwell
 *
 */
class DefaultSettlementDateCalculator implements SettlementDateCalculator {

	private final List<DayOfWeek> nonWorkingDays;

	/**
	 * By default, this date calculator will adjust settlement dates on Saturday or
	 * Sunday to lie on the following Monday.
	 */
	DefaultSettlementDateCalculator() {
		this(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
	}

	/**
	 * This calculator will skip over any days of the week specified here when
	 * getting the adjusted settlement date. Will throw an exception if you try to
	 * make the whole week non-working.
	 * 
	 * @param nonWorkingDays The days we do not want a settlement date to lie on
	 */
	DefaultSettlementDateCalculator(DayOfWeek... nonWorkingDays) {
		if (nonWorkingDays == null) {
			throw new IllegalArgumentException("Cannot specifiy null for nonWorkingDays");
		}
		if (nonWorkingDays.length == DayOfWeek.values().length) {
			throw new IllegalArgumentException("Must have at least 1 working day");
		}
		this.nonWorkingDays = Arrays.asList(nonWorkingDays);
	}

	@Override
	public LocalDate getAdjustedSettlementDate(Instruction instruction) {
		// Increment date until no longer lying on a nonWorkingDay
		LocalDate adjustedDate = instruction.getSettlementDate();
		while (nonWorkingDays.contains(adjustedDate.getDayOfWeek())) {
			adjustedDate = adjustedDate.plus(Period.ofDays(1));
		}
		return adjustedDate;
	}

}
