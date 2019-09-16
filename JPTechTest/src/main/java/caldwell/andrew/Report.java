package caldwell.andrew;

import java.time.LocalDate;
import java.util.List;

import caldwell.andrew.result.ReportResult;

/**
 * Produces a report based on a list of instructions. The report will show the
 * total amount ingoing/outgoing split by date, and it will show the entities
 * ranked by their largest ingoing/outgoing instruction.
 * 
 * @author acaldwell
 *
 */
public class Report {

	public Report() {
	}

	/**
	 * For a given list of instructions, construct and report the report result
	 * 
	 * @param instructions The report instructions
	 * @return The report result
	 */
	public ReportResult generateReport(List<Instruction> instructions) {
		// Build report
		ReportResult result = new ReportResult();
		for (Instruction instruction : instructions) {
			result.addInstruction(instruction);
		}
		return result;
	}

	/**
	 * Prints the supplied report result to the console
	 * 
	 * @param reportResult The report result
	 */
	public void printReport(ReportResult reportResult) {
		// Print Amounts By Date Report
		System.out.println("Date\t\tOutgoing\tIncoming");
		for (LocalDate date : reportResult.getAllDates()) {
			System.out.println(date + "\t" + reportResult.getTotal(InstructionType.BUY, date) + "\t\t"
					+ reportResult.getTotal(InstructionType.SELL, date));
		}

		// Print Ranked Entity Report
		System.out.println("\nEntity\tOutgoing Rank\tIncoming Rank");

		List<String> entitiesRankedByOutgoing = reportResult.getRankedEntities(InstructionType.BUY);
		List<String> entitiesRankedByIncoming = reportResult.getRankedEntities(InstructionType.SELL);
		for (String entity : reportResult.getAllEntities()) {
			System.out.println(entity + "\t" + getRank(entitiesRankedByOutgoing, entity) + "\t\t" + getRank(entitiesRankedByIncoming, entity));
		}
	}

	/**
	 * 
	 * @param ranking The ranking to use
	 * @param entity  The entity we are looking for
	 * @return Where in the list the entity occurs, or null if not found
	 */
	private String getRank(List<String> ranking, String entity) {
		String rankingStr = "n/a";
		if (ranking.contains(entity)) {
			rankingStr = String.valueOf(ranking.indexOf(entity)+1);
		}
		return rankingStr;
	}
}
