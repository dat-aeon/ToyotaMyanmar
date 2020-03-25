package com.mttl.vlms.common.selectmapper;

import java.util.List;

import com.mttl.vlms.common.selectdto.ChargesSelectDto;
import com.mttl.vlms.common.selectdto.ChassisNoSelectDto;
import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.common.selectdto.DriverSelectDto;
import com.mttl.vlms.common.selectdto.InvoiceSelectDto;
import com.mttl.vlms.common.selectdto.PortSelectDto;
import com.mttl.vlms.common.selectdto.RoleSelectDto;
import com.mttl.vlms.common.selectdto.StateDivisionInfoSelectDto;
import com.mttl.vlms.common.selectdto.TaskSelectDto;
import com.mttl.vlms.common.selectdto.VehicleInfoSelectDto;
import com.mttl.vlms.common.selectdto.YardSelectDto;
import com.mttl.vlms.operation.opr001.dto.BankAccountDto;

/**
 * SelectMapper
 * 
 * 
 */
public interface SelectMapper {

	List<YardSelectDto> getYardList();

	List<DriverSelectDto> getDriverList();

	List<StateDivisionInfoSelectDto> getStateDivisionList();

	List<DealerSelectDto> getDealerList();

	List<ChargesSelectDto> getChargesList();

	List<VehicleInfoSelectDto> getVehicleInfoList();

	List<TaskSelectDto> getTaskList();

	List<PortSelectDto> getPortList();

	List<ChassisNoSelectDto> getChassisNoList();

	List<RoleSelectDto> getRoleList();

	List<BankAccountDto> getBankAccountList();

	List<InvoiceSelectDto> getInvoiceList();

	boolean getShowPublic();
}