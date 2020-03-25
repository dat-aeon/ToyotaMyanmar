package com.mttl.vlms.operation.opr001.dto;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.mttl.vlms.common.AbstractDto;
import com.mttl.vlms.common.selectdto.ChargesSelectDto;

/**
 * ItemDto
 * 
 * 
 *
 */
public class ItemDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5817803469010621819L;

	private ChargesSelectDto chargesSelectDto;

	private Integer chargesId;

	private int id;

	private int invoiceId;

	private int unit;

	private BigDecimal unitPrice;

	private BigDecimal amount;

	private Boolean deleteFlg;

	private String description;

	private ItemVehicleSearchReqDto itemVehicleSearchReqDto;

	private List<ItemVehicleDto> itemVehicleDtoList;

	public ChargesSelectDto getChargesSelectDto() {
		return chargesSelectDto;
	}

	public void setChargesSelectDto(ChargesSelectDto chargesSelectDto) {
		this.chargesSelectDto = chargesSelectDto;
	}

	public Integer getChargesId() {
		return chargesId;
	}

	public void setChargesId(Integer chargesId) {
		this.chargesId = chargesId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Boolean getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(Boolean deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ItemVehicleSearchReqDto getItemVehicleSearchReqDto() {
		return itemVehicleSearchReqDto;
	}

	public void setItemVehicleSearchReqDto(ItemVehicleSearchReqDto itemVehicleSearchReqDto) {
		this.itemVehicleSearchReqDto = itemVehicleSearchReqDto;
	}

	public List<ItemVehicleDto> getItemVehicleDtoList() {
		return itemVehicleDtoList;
	}

	public void setItemVehicleDtoList(List<ItemVehicleDto> itemVehicleDtoList) {
		this.itemVehicleDtoList = itemVehicleDtoList;
	}

	@Override
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
