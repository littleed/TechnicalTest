package caldwell.andrew.settlementdate;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

import caldwell.andrew.CurrencyConstants;

/**
 * Factory class that will return the <code>SettlementDateCalculator</code>
 * suitable for Instructions on the specified currencyType.
 *
 */
public class SettlementDateCalculatorFactory {

	private final SettlementDateCalculator defaultDateCalculator;
	private final Map<String, SettlementDateCalculator> dateCalculatorMap;

	public SettlementDateCalculatorFactory() {
		// The SettlementDateCalculator to use by default
		defaultDateCalculator = new DefaultSettlementDateCalculator();
		// Register all SettlementDateCalculators in an internal map so they can be
		// accessed rapidly
		dateCalculatorMap = new HashMap<String, SettlementDateCalculator>();
		SettlementDateCalculator sunMonSettlementDateCalculator = new DefaultSettlementDateCalculator(DayOfWeek.FRIDAY,
				DayOfWeek.SATURDAY);
		dateCalculatorMap.put(CurrencyConstants.AED, sunMonSettlementDateCalculator);
		dateCalculatorMap.put(CurrencyConstants.SAR, sunMonSettlementDateCalculator);
	}

	/**
	 * 
	 * @param currencyType The currency type we want the data calculator for,
	 *                     expressed as a 3 character initialism
	 * @return The appropriate settlement date calculator for the provided
	 *         currencyType.
	 */
	public SettlementDateCalculator getSettlementDateCalculator(String currencyType) {
		if (dateCalculatorMap.containsKey(currencyType)) {
			return dateCalculatorMap.get(currencyType);
		} else {
			return defaultDateCalculator;
		}
	}

}
