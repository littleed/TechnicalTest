package caldwell.andrew.result;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import caldwell.andrew.result.EntityRank;

/**
 * Tests EntityRank
 * 
 * @author acaldwell
 *
 */
public class TestEntityRank {

	private EntityRank entityRank;

	@Before
	public void setup() {
		entityRank = new EntityRank();
	}

	/**
	 * Test a null entity causes exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNullEntity() {
		entityRank.addInstruction(null, BigDecimal.ZERO);
	}

	/**
	 * Test a null amount causes exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNullAmount() {
		entityRank.addInstruction("", null);
	}

	/**
	 * Tests getRankedEntities
	 */
	@Test
	public void testGetRankedEntities() {
		String e1 = "e1";
		String e2 = "e2";
		Assert.assertEquals(0, entityRank.getRankedEntities().size());
		entityRank.addInstruction(e1, new BigDecimal(1));
		entityRank.addInstruction(e2, new BigDecimal(2));

		// Check highest value (e2) is first
		List<String> result = entityRank.getRankedEntities();
		Assert.assertEquals(0, result.indexOf(e2));
		Assert.assertEquals(1, result.indexOf(e1));

		entityRank.addInstruction(e1, new BigDecimal(3));

		// Check new highest value (e1) is first
		result = entityRank.getRankedEntities();
		Assert.assertEquals(0, result.indexOf(e1));
		Assert.assertEquals(1, result.indexOf(e2));
	}
}
