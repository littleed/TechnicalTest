package caldwell.andrew.result;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the InstructionTotalsOnDate
 * 
 * @author acaldwell
 *
 */
public class TestInstructionTotalsOnDate {

	private InstructionTotalsOnDate instructionTotalsOnDate;

	@Before
	public void setup() {
		instructionTotalsOnDate = new InstructionTotalsOnDate();
	}

	/**
	 * Tests null date causes exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNullDate() {
		instructionTotalsOnDate.addAmount(null, BigDecimal.ZERO);
	}

	/**
	 * Tests null amount causes exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNullAmount() {
		instructionTotalsOnDate.addAmount(LocalDate.now(), null);
	}

	/**
	 * Tests amounts added are returned correctly
	 */
	@Test
	public void testAddAmounts() {
		LocalDate d1 = LocalDate.of(2019, Month.SEPTEMBER, 2);
		LocalDate d2 = LocalDate.of(2019, Month.SEPTEMBER, 3);
		instructionTotalsOnDate.addAmount(d1, new BigDecimal(1));
		instructionTotalsOnDate.addAmount(d1, new BigDecimal(1));
		instructionTotalsOnDate.addAmount(d2, new BigDecimal(3));
		// Check totals correct
		Assert.assertEquals(new BigDecimal(2), instructionTotalsOnDate.getTotalOnDate(d1));
		Assert.assertEquals(new BigDecimal(3), instructionTotalsOnDate.getTotalOnDate(d2));
	}
}
