/**
 * 
 */
package caldwell.andrew;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import caldwell.andrew.result.ReportResult;

/**
 * Test the Report
 * @author acaldwell
 *
 */
public class TestReport {

	private Report report;
	
	@Before
	public void setup() {
		report = new Report();
	}
	
	/**
	 * Tests the report for a health test set of data that should cover all aspects 
	 * of the report
	 */
	@Test
	public void testReport() {
		ReportResult results = report.generateReport(Arrays.asList(
				new Instruction("Foo", InstructionType.BUY, new BigDecimal("0.8"), "GBP", 
						LocalDate.of(2019, Month.SEPTEMBER, 2), 
						LocalDate.of(2019, Month.SEPTEMBER, 3), 14, new BigDecimal("2.2")),
				new Instruction("Foo", InstructionType.BUY, new BigDecimal("0.8"), "GBP", 
						LocalDate.of(2019, Month.SEPTEMBER, 3), 
						LocalDate.of(2019, Month.SEPTEMBER, 4), 15, new BigDecimal("2.2")),
				new Instruction("Foo", InstructionType.BUY, new BigDecimal("0.8"), "GBP", 
						LocalDate.of(2019, Month.SEPTEMBER, 4), 
						LocalDate.of(2019, Month.SEPTEMBER, 5), 20, new BigDecimal("2.2")),
				new Instruction("Foo", InstructionType.BUY, new BigDecimal("0.8"), "GBP", 
						LocalDate.of(2019, Month.SEPTEMBER, 5), 
						LocalDate.of(2019, Month.SEPTEMBER, 6), 42, new BigDecimal("2.2")),
				new Instruction("Foo", InstructionType.SELL, new BigDecimal("0.8"), "GBP",  
						LocalDate.of(2019, Month.SEPTEMBER, 6), 
						LocalDate.of(2019, Month.SEPTEMBER, 7), 16, new BigDecimal("2.2")),
				new Instruction("Foo", InstructionType.BUY, new BigDecimal("0.8"), "GBP",  
						LocalDate.of(2019, Month.SEPTEMBER, 7), 
						LocalDate.of(2019, Month.SEPTEMBER, 8), 0, new BigDecimal("52")),
				new Instruction("Foo", InstructionType.BUY, new BigDecimal("0.8"), "GBP",  
						LocalDate.of(2019, Month.SEPTEMBER, 8), 
						LocalDate.of(2019, Month.SEPTEMBER, 9), 1, new BigDecimal("2.2")),
				new Instruction("Foo", InstructionType.SELL, new BigDecimal("0.8"), "GBP", 
						LocalDate.of(2019, Month.SEPTEMBER, 9), 
						LocalDate.of(2019, Month.SEPTEMBER, 10), 3, new BigDecimal("2.2")),
				new Instruction("Foo", InstructionType.BUY, new BigDecimal("0.8"), "GBP", 
						LocalDate.of(2019, Month.SEPTEMBER, 10), 
						LocalDate.of(2019, Month.SEPTEMBER, 11), 24, new BigDecimal("2.2")),
				new Instruction("Foo", InstructionType.BUY, new BigDecimal("0.8"), "GBP", 
						LocalDate.of(2019, Month.SEPTEMBER, 11), 
						LocalDate.of(2019, Month.SEPTEMBER, 12), 55, new BigDecimal("2.2")),
				new Instruction("Foo", InstructionType.BUY, new BigDecimal("0.8"), "GBP",  
						LocalDate.of(2019, Month.SEPTEMBER, 12), 
						LocalDate.of(2019, Month.SEPTEMBER, 13), 60, new BigDecimal("2.2")),
				new Instruction("Bar", InstructionType.BUY, new BigDecimal("0.8"), "GBP", 
						LocalDate.of(2019, Month.SEPTEMBER, 2), 
						LocalDate.of(2019, Month.SEPTEMBER, 3), 14, new BigDecimal("2.2")),
				new Instruction("Bar", InstructionType.BUY, new BigDecimal("0.8"), "GBP",  
						LocalDate.of(2019, Month.SEPTEMBER, 3), 
						LocalDate.of(2019, Month.SEPTEMBER, 4), 15, new BigDecimal("2.2")),
				new Instruction("Bar", InstructionType.BUY, new BigDecimal("0.8"), "GBP", 
						LocalDate.of(2019, Month.SEPTEMBER, 4), 
						LocalDate.of(2019, Month.SEPTEMBER, 5), 20, new BigDecimal("2.2")),
				new Instruction("Bar", InstructionType.BUY, new BigDecimal("0.8"), "GBP",  
						LocalDate.of(2019, Month.SEPTEMBER, 5), 
						LocalDate.of(2019, Month.SEPTEMBER, 6),42, new BigDecimal("2.2")),
				new Instruction("Bar", InstructionType.SELL, new BigDecimal("0.8"), "GBP",  
						LocalDate.of(2019, Month.SEPTEMBER, 6), 
						LocalDate.of(2019, Month.SEPTEMBER, 7), 16, new BigDecimal("2.2")),
				new Instruction("Bar", InstructionType.BUY, new BigDecimal("0.8"), "GBP",  
						LocalDate.of(2019, Month.SEPTEMBER, 7), 
						LocalDate.of(2019, Month.SEPTEMBER, 8), 0, new BigDecimal("52")),
				new Instruction("Bar", InstructionType.BUY, new BigDecimal("0.8"), "GBP",  
						LocalDate.of(2019, Month.SEPTEMBER, 8), 
						LocalDate.of(2019, Month.SEPTEMBER, 9),1, new BigDecimal("2.2")),
				new Instruction("Bar", InstructionType.SELL, new BigDecimal("0.8"), "GBP",  
						LocalDate.of(2019, Month.SEPTEMBER, 9), 
						LocalDate.of(2019, Month.SEPTEMBER, 10), 3, new BigDecimal("2.2")),
				new Instruction("Bar", InstructionType.BUY, new BigDecimal("0.8"), "GBP",  
						LocalDate.of(2019, Month.SEPTEMBER, 10), 
						LocalDate.of(2019, Month.SEPTEMBER, 11), 24, new BigDecimal("2.2")),
				new Instruction("Bar", InstructionType.BUY, new BigDecimal("0.8"), "GBP",  
						LocalDate.of(2019, Month.SEPTEMBER, 11), 
						LocalDate.of(2019, Month.SEPTEMBER, 12), 55, new BigDecimal("2.2")),
				new Instruction("Bar", InstructionType.BUY, new BigDecimal("0.8"), "GBP",  
						LocalDate.of(2019, Month.SEPTEMBER, 12), 
						LocalDate.of(2019, Month.SEPTEMBER, 13), 60, new BigDecimal("32")),
				new Instruction("Two", InstructionType.SELL, new BigDecimal("1"), "USD",  
						LocalDate.of(2019, Month.SEPTEMBER, 2), 
						LocalDate.of(2019, Month.SEPTEMBER, 3), 5000, new BigDecimal("4000"))
						));
		
		//Check Dates
		List<LocalDate> dates = results.getAllDates();
		Assert.assertEquals(9, dates.size());
		Assert.assertEquals(LocalDate.of(2019, Month.SEPTEMBER, 3), dates.get(0));
		Assert.assertEquals(LocalDate.of(2019, Month.SEPTEMBER, 4), dates.get(1));
		Assert.assertEquals(LocalDate.of(2019, Month.SEPTEMBER, 5), dates.get(2));
		Assert.assertEquals(LocalDate.of(2019, Month.SEPTEMBER, 6), dates.get(3));
		Assert.assertEquals(LocalDate.of(2019, Month.SEPTEMBER, 9), dates.get(4));
		Assert.assertEquals(LocalDate.of(2019, Month.SEPTEMBER, 10), dates.get(5));
		Assert.assertEquals(LocalDate.of(2019, Month.SEPTEMBER, 11), dates.get(6));
		Assert.assertEquals(LocalDate.of(2019, Month.SEPTEMBER, 12), dates.get(7));
		Assert.assertEquals(LocalDate.of(2019, Month.SEPTEMBER, 13), dates.get(8));
		
		//Check outgoing
		Assert.assertEquals(new BigDecimal("49.28"), results.getTotal(InstructionType.BUY, LocalDate.of(2019, Month.SEPTEMBER, 3)));
		Assert.assertEquals(new BigDecimal("52.80"), results.getTotal(InstructionType.BUY, LocalDate.of(2019, Month.SEPTEMBER, 4)));
		Assert.assertEquals(new BigDecimal("70.40"), results.getTotal(InstructionType.BUY, LocalDate.of(2019, Month.SEPTEMBER, 5)));
		Assert.assertEquals(new BigDecimal("147.84"), results.getTotal(InstructionType.BUY, LocalDate.of(2019, Month.SEPTEMBER, 6)));
		Assert.assertEquals(new BigDecimal("3.52"), results.getTotal(InstructionType.BUY, LocalDate.of(2019, Month.SEPTEMBER, 9)));
		Assert.assertEquals(BigDecimal.ZERO, results.getTotal(InstructionType.BUY, LocalDate.of(2019, Month.SEPTEMBER, 10)));
		Assert.assertEquals(new BigDecimal("84.48"), results.getTotal(InstructionType.BUY, LocalDate.of(2019, Month.SEPTEMBER, 11)));
		Assert.assertEquals(new BigDecimal("193.60"), results.getTotal(InstructionType.BUY, LocalDate.of(2019, Month.SEPTEMBER, 12)));
		Assert.assertEquals(new BigDecimal("1641.60"), results.getTotal(InstructionType.BUY, LocalDate.of(2019, Month.SEPTEMBER, 13)));
		
		//Check incoming
		Assert.assertEquals(new BigDecimal("20000000.00"), results.getTotal(InstructionType.SELL, LocalDate.of(2019, Month.SEPTEMBER, 3)));
		Assert.assertEquals(BigDecimal.ZERO, results.getTotal(InstructionType.SELL, LocalDate.of(2019, Month.SEPTEMBER, 4)));
		Assert.assertEquals(BigDecimal.ZERO, results.getTotal(InstructionType.SELL, LocalDate.of(2019, Month.SEPTEMBER, 5)));
		Assert.assertEquals(BigDecimal.ZERO, results.getTotal(InstructionType.SELL, LocalDate.of(2019, Month.SEPTEMBER, 6)));
		Assert.assertEquals(new BigDecimal("56.32"), results.getTotal(InstructionType.SELL, LocalDate.of(2019, Month.SEPTEMBER, 9)));
		Assert.assertEquals(new BigDecimal("10.56"), results.getTotal(InstructionType.SELL, LocalDate.of(2019, Month.SEPTEMBER, 10)));
		Assert.assertEquals(BigDecimal.ZERO, results.getTotal(InstructionType.SELL, LocalDate.of(2019, Month.SEPTEMBER, 11)));
		Assert.assertEquals(BigDecimal.ZERO, results.getTotal(InstructionType.SELL, LocalDate.of(2019, Month.SEPTEMBER, 12)));
		Assert.assertEquals(BigDecimal.ZERO, results.getTotal(InstructionType.SELL, LocalDate.of(2019, Month.SEPTEMBER, 13)));
		
		//Check ranking
		List<String> outgoingRanking = results.getRankedEntities(InstructionType.BUY);
		Assert.assertEquals(2, outgoingRanking.size());
		Assert.assertEquals("Bar", outgoingRanking.get(0));
		Assert.assertEquals("Foo", outgoingRanking.get(1));
		
		List<String> incomingRanking = results.getRankedEntities(InstructionType.SELL);
		Assert.assertEquals(3, incomingRanking.size());
		Assert.assertEquals("Two", incomingRanking.get(0));
		Assert.assertEquals("Bar", incomingRanking.get(1));
		Assert.assertEquals("Foo", incomingRanking.get(2));
	}
}
