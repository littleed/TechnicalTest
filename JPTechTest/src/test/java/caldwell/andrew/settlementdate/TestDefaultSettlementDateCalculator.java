package caldwell.andrew.settlementdate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

import org.junit.Assert;
import org.junit.Test;

import caldwell.andrew.Instruction;

/**
 * Tests the DefaultSettlementDateCalculator
 * 
 * @author acaldwell
 *
 */
public class TestDefaultSettlementDateCalculator {

	/**
	 * Test that an exception is thrown if you try to pass all week days as
	 * nonWorkingDays.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTooManynonWorkingDays() {
		new DefaultSettlementDateCalculator(DayOfWeek.values());
	}

	/**
	 * Test that an exception is thrown if you pass a null array to the constructor.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNullArray() {
		new DefaultSettlementDateCalculator((DayOfWeek[]) null);
	}

	/**
	 * Test an exception is thrown if a null instruction is passed to the
	 * calculation method
	 */
	@Test(expected = NullPointerException.class)
	public void testNullInstruction() {
		DefaultSettlementDateCalculator calc = new DefaultSettlementDateCalculator();
		calc.getAdjustedSettlementDate(null);
	}

	/**
	 * Test an exception is thrown when an instruction with a null settlement date
	 * is passed
	 */
	@Test(expected = NullPointerException.class)
	public void testNullSettlementDate() {
		DefaultSettlementDateCalculator calc = new DefaultSettlementDateCalculator();
		calc.getAdjustedSettlementDate(createInstruction(null));
	}

	/**
	 * Test the DefaultSettlementDateCalculator correctly returns a settlement date
	 * equal to the instructions settlement date for all weekdays other than
	 * Saturday/Sunday, in which case the following Monday will be returned.
	 */
	@Test
	public void testDefaultSettlementDates() {
		DefaultSettlementDateCalculator calc = new DefaultSettlementDateCalculator();
		LocalDate localDate = LocalDate.of(2019, Month.SEPTEMBER, 9);// Monday 9th September
		Assert.assertEquals(localDate, calc.getAdjustedSettlementDate(createInstruction(localDate)));
		localDate = localDate.plus(Period.ofDays(1));// Tuesday
		Assert.assertEquals(localDate, calc.getAdjustedSettlementDate(createInstruction(localDate)));
		localDate = localDate.plus(Period.ofDays(1));// Wednesday
		Assert.assertEquals(localDate, calc.getAdjustedSettlementDate(createInstruction(localDate)));
		localDate = localDate.plus(Period.ofDays(1));// Thursday
		Assert.assertEquals(localDate, calc.getAdjustedSettlementDate(createInstruction(localDate)));
		localDate = localDate.plus(Period.ofDays(1));// Friday
		Assert.assertEquals(localDate, calc.getAdjustedSettlementDate(createInstruction(localDate)));
		LocalDate followingMonday = LocalDate.of(2019, Month.SEPTEMBER, 16);
		localDate = localDate.plus(Period.ofDays(1));// Saturday
		Assert.assertEquals(followingMonday, calc.getAdjustedSettlementDate(createInstruction(localDate)));
		localDate = localDate.plus(Period.ofDays(1));// Sunday
		Assert.assertEquals(followingMonday, calc.getAdjustedSettlementDate(createInstruction(localDate)));
	}

	/**
	 * Test the DefaultSettlementDateCalculator correctly returns a settlement date
	 * equal to the instructions settlement date for all weekdays other than the
	 * week days specified, in which case the following weekday not specified will
	 * be returned.
	 */
	@Test
	public void testAlternateSettlementDates() {
		DefaultSettlementDateCalculator calc = new DefaultSettlementDateCalculator(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
		LocalDate localDate = LocalDate.of(2019, Month.SEPTEMBER, 9);// Monday 9th September
		
		Assert.assertEquals(localDate, calc.getAdjustedSettlementDate(createInstruction(localDate)));
		localDate = localDate.plus(Period.ofDays(1));// Tuesday
		Assert.assertEquals(localDate, calc.getAdjustedSettlementDate(createInstruction(localDate)));
		localDate = localDate.plus(Period.ofDays(1));// Wednesday
		Assert.assertEquals(localDate, calc.getAdjustedSettlementDate(createInstruction(localDate)));
		localDate = localDate.plus(Period.ofDays(1));// Thursday
		Assert.assertEquals(localDate, calc.getAdjustedSettlementDate(createInstruction(localDate)));
		LocalDate followingSunday = LocalDate.of(2019, Month.SEPTEMBER, 15);
		localDate = localDate.plus(Period.ofDays(1));// Friday
		Assert.assertEquals(followingSunday, calc.getAdjustedSettlementDate(createInstruction(localDate)));
		localDate = localDate.plus(Period.ofDays(1));// Saturday
		Assert.assertEquals(followingSunday, calc.getAdjustedSettlementDate(createInstruction(localDate)));
		localDate = localDate.plus(Period.ofDays(1));// Sunday
		Assert.assertEquals(localDate, calc.getAdjustedSettlementDate(createInstruction(localDate)));
	}

	/**
	 * Test the DefaultSettlementDateCalculator correctly returns a settlement date
	 * equal to the instructions settlement date when no nonworkingdays are
	 * specified
	 */
	@Test
	public void testNoNonWorkingDays() {
		DefaultSettlementDateCalculator calc = new DefaultSettlementDateCalculator(new DayOfWeek[] {});
		LocalDate localDate = LocalDate.of(2019, Month.SEPTEMBER, 9);// Monday 9th September
		Assert.assertEquals(localDate, calc.getAdjustedSettlementDate(createInstruction(localDate)));
		localDate = localDate.plus(Period.ofDays(1));// Tuesday
		Assert.assertEquals(localDate, calc.getAdjustedSettlementDate(createInstruction(localDate)));
		localDate = localDate.plus(Period.ofDays(1));// Wednesday
		Assert.assertEquals(localDate, calc.getAdjustedSettlementDate(createInstruction(localDate)));
		localDate = localDate.plus(Period.ofDays(1));// Thursday
		Assert.assertEquals(localDate, calc.getAdjustedSettlementDate(createInstruction(localDate)));
		localDate = localDate.plus(Period.ofDays(1));// Friday
		Assert.assertEquals(localDate, calc.getAdjustedSettlementDate(createInstruction(localDate)));
		localDate = localDate.plus(Period.ofDays(1));// Saturday
		Assert.assertEquals(localDate, calc.getAdjustedSettlementDate(createInstruction(localDate)));
		localDate = localDate.plus(Period.ofDays(1));// Sunday
		Assert.assertEquals(localDate, calc.getAdjustedSettlementDate(createInstruction(localDate)));

	}

	/**
	 * Utilty method for creating an Instruction containing just the specified
	 * settlement date.
	 * 
	 * @param settlementDate The date we want in the Instruction's settlement date
	 * @return A created Instruction
	 */
	private Instruction createInstruction(LocalDate settlementDate) {
		return new Instruction(null, null, null, null, null, settlementDate, null, null);
	}

}
