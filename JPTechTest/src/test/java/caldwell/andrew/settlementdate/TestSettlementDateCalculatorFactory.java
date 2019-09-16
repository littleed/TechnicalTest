package caldwell.andrew.settlementdate;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import caldwell.andrew.CurrencyConstants;
import caldwell.andrew.Instruction;

/**
 * Tests the SettlementDateCalculatorFactory
 * 
 * @author acaldwell
 *
 */
public class TestSettlementDateCalculatorFactory {

	private SettlementDateCalculatorFactory factory;

	@Before
	public void setup() {
		factory = new SettlementDateCalculatorFactory();
	}

	/**
	 * Test the correct calculator is returned by default by checking Friday is a
	 * working day.
	 */
	@Test
	public void testDefaultCalculator() {
		SettlementDateCalculator calc = factory.getSettlementDateCalculator(null);
		LocalDate ld = LocalDate.of(2019, Month.SEPTEMBER, 13); // FRIDAY
		Assert.assertEquals(ld, calc.getAdjustedSettlementDate(createInstruction(ld)));
	}

	/**
	 * Test the correct calculator is returned for currency AED by checking Sunday
	 * is a working day.
	 */
	@Test
	public void testAEDCalculator() {
		SettlementDateCalculator calc = factory.getSettlementDateCalculator(CurrencyConstants.AED);
		LocalDate ld = LocalDate.of(2019, Month.SEPTEMBER, 15); // SUNDAY
		Assert.assertEquals(ld, calc.getAdjustedSettlementDate(createInstruction(ld)));
	}

	/**
	 * Test the correct calculator is returned for currency SAR by checking Sunday
	 * is a working day.
	 */
	@Test
	public void testSARCalculator() {
		SettlementDateCalculator calc = factory.getSettlementDateCalculator(CurrencyConstants.SAR);
		LocalDate ld = LocalDate.of(2019, Month.SEPTEMBER, 15); // SUNDAY
		Assert.assertEquals(ld, calc.getAdjustedSettlementDate(createInstruction(ld)));
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
