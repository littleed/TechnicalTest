package caldwell.andrew;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a single instruction
 * 
 * @author acaldwell
 *
 */
public class Instruction {

	private String entity;
	private InstructionType type;
	private BigDecimal agreedFx;
	private String currency;
	private LocalDate instructionDate;
	private LocalDate settlementDate;
	private Integer units;
	private BigDecimal pricePerUnit;

	/**
	 * Construct an instruction with the following arguments
	 * 
	 * @param entity
	 * @param type
	 * @param agreedFx
	 * @param currency
	 * @param instructionDate
	 * @param settlementDate
	 * @param units
	 * @param pricePerUnit
	 */
	public Instruction(String entity, InstructionType type, BigDecimal agreedFx, String currency,
			LocalDate instructionDate, LocalDate settlementDate, Integer units, BigDecimal pricePerUnit) {
		super();
		this.entity = entity;
		this.type = type;
		this.agreedFx = agreedFx;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.units = units;
		this.pricePerUnit = pricePerUnit;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public InstructionType getType() {
		return type;
	}

	public void setType(InstructionType type) {
		this.type = type;
	}

	public BigDecimal getAgreedFx() {
		return agreedFx;
	}

	public void setAgreedFx(BigDecimal agreedFx) {
		this.agreedFx = agreedFx;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public LocalDate getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(LocalDate instructionDate) {
		this.instructionDate = instructionDate;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
}
