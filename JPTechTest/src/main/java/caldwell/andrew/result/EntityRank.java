package caldwell.andrew.result;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * By adding entities and their instruction amounts, this class will produce a
 * list of entities in order of instruction amount, highest to lowest.
 * 
 * @author acaldwell
 *
 */
public class EntityRank {

	private final Map<String, BigDecimal> highestInstruction;

	public EntityRank() {
		highestInstruction = new HashMap<String, BigDecimal>();
	}

	/**
	 * 
	 * @param entity The entity the instruction amount is for. Cannot be null.
	 * @param amount The total instruction amount in USD. Cannot be null.
	 */
	public void addInstruction(String entity, BigDecimal amount) {
		if (amount == null || entity == null) {
			throw new IllegalArgumentException("Must provide an entity and amount to add");
		}
		if (!highestInstruction.containsKey(entity)) {
			highestInstruction.put(entity, amount);
		} else {
			highestInstruction.put(entity, highestInstruction.get(entity).max(amount));
		}
	}

	/**
	 * 
	 * @return All entities added to this class, in order of their largest
	 *         instruction amount
	 */
	public List<String> getRankedEntities() {
		return highestInstruction.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry<String, BigDecimal>::getValue)
				.reversed())
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}

}
