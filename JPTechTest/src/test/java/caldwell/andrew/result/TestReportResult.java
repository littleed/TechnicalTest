/**
 * 
 */
package caldwell.andrew.result;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import caldwell.andrew.Instruction;
import caldwell.andrew.InstructionType;

/**
 * 
 * Tests the ReportResult
 * 
 * @author acaldwell
 *
 */
public class TestReportResult {

	private ReportResult result;

	@Before
	public void setup() {
		result = new ReportResult();
	}

	/**
	 * Tests getAllDates
	 */
	@Test
	public void testGetAllDates() {
		Assert.assertEquals(0, result.getAllDates().size());
		Instruction i1 = new Instruction("Foo", InstructionType.BUY, BigDecimal.ONE, "USD",
				LocalDate.of(2010, Month.JANUARY, 1), LocalDate.of(2019, Month.SEPTEMBER, 3), 4, BigDecimal.ONE);
		Instruction i2 = new Instruction("Foo", InstructionType.BUY, BigDecimal.ONE, "USD",
				LocalDate.of(2010, Month.JANUARY, 1), LocalDate.of(2019, Month.SEPTEMBER, 2), 4, BigDecimal.ONE);
		result.addInstruction(i1);
		result.addInstruction(i2);
		List<LocalDate> dates = result.getAllDates();
		Assert.assertEquals(2, dates.size());
		Assert.assertEquals(i2.getSettlementDate(), dates.get(0));
		Assert.assertEquals(i1.getSettlementDate(), dates.get(1));
	}

	/**
	 * Tests getRankedEntityTotals
	 */
	@Test
	public void testGetRankedEntities() {
		// Couple of buys
		Instruction i1 = new Instruction("Foo", InstructionType.BUY, BigDecimal.ONE, "USD",
				LocalDate.of(2010, Month.JANUARY, 1), LocalDate.of(2019, Month.SEPTEMBER, 3), 5, BigDecimal.ONE);
		Instruction i2 = new Instruction("Bar", InstructionType.BUY, BigDecimal.ONE, "USD",
				LocalDate.of(2010, Month.JANUARY, 1), LocalDate.of(2019, Month.SEPTEMBER, 3), 4, BigDecimal.ONE);

		// Couple of sells
		Instruction i3 = new Instruction("Foo", InstructionType.SELL, BigDecimal.ONE, "USD",
				LocalDate.of(2010, Month.JANUARY, 1), LocalDate.of(2019, Month.SEPTEMBER, 3), 3, BigDecimal.ONE);
		Instruction i4 = new Instruction("Bar", InstructionType.SELL, BigDecimal.ONE, "USD",
				LocalDate.of(2010, Month.JANUARY, 1), LocalDate.of(2019, Month.SEPTEMBER, 3), 6, BigDecimal.ONE);

		result.addInstruction(i1);
		result.addInstruction(i2);
		result.addInstruction(i3);
		result.addInstruction(i4);
		
		List<String> rankedEntities = result.getRankedEntities(InstructionType.BUY);
		Assert.assertEquals("Foo", rankedEntities.get(0));
		Assert.assertEquals("Bar", rankedEntities.get(1));
		rankedEntities = result.getRankedEntities(InstructionType.SELL);
		Assert.assertEquals("Bar", rankedEntities.get(0));
		Assert.assertEquals("Foo", rankedEntities.get(1));
	}
	
	/**
	 * Tests getTotal
	 */
	@Test
	public void testGetTotal() {

		Instruction i1 = new Instruction("Foo", InstructionType.BUY, BigDecimal.ONE, "USD",
				LocalDate.of(2010, Month.JANUARY, 1), LocalDate.of(2019, Month.SEPTEMBER, 3), 1, BigDecimal.ONE);
		Instruction i2 = new Instruction("Foo", InstructionType.BUY, BigDecimal.ONE, "USD",
				LocalDate.of(2010, Month.JANUARY, 1), LocalDate.of(2019, Month.SEPTEMBER, 3), 4, BigDecimal.ONE);
		
		Instruction i3 = new Instruction("Foo", InstructionType.SELL, BigDecimal.ONE, "USD",
				LocalDate.of(2010, Month.JANUARY, 1), LocalDate.of(2019, Month.SEPTEMBER, 3), 3, BigDecimal.ONE);
		

		result.addInstruction(i1);
		result.addInstruction(i2);
		result.addInstruction(i3);
		
		Assert.assertEquals(new BigDecimal("3.00"), result.getTotal(InstructionType.SELL, LocalDate.of(2019, Month.SEPTEMBER, 3)));
		Assert.assertEquals(BigDecimal.ZERO, result.getTotal(InstructionType.SELL, LocalDate.of(2019, Month.SEPTEMBER, 4)));
		Assert.assertEquals(new BigDecimal("5.00"), result.getTotal(InstructionType.BUY, LocalDate.of(2019, Month.SEPTEMBER, 3)));
	}

	/**
	 * Tests a null amount throws an exception
	 */
	@Test(expected = Exception.class)
	public void testGetAmountToTradeUSDNull() {
		result.getAmountToTradeUSD(null);
	}

	/**
	 * Tests getAmountToTradeUSD
	 */
	@Test
	public void testGetAmountToTradeUSD() {
		// Test calculation works and doesn't fall into the same pitfalls as floating
		// points
		Instruction i1 = new Instruction(null, null, new BigDecimal("0.8"), null, null, null, 4,
				new BigDecimal("20.20"));
		Assert.assertEquals(new BigDecimal("64.64"), result.getAmountToTradeUSD(i1));
	}

}
