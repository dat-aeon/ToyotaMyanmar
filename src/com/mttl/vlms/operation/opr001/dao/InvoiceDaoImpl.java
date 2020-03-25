package com.mttl.vlms.operation.opr001.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.BasicDAO;
import com.mttl.vlms.common.BeanConverter;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.common.selectdto.ChargesSelectDto;
import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.common.selectdto.TaskSelectDto;
import com.mttl.vlms.common.selectmapper.SelectMapper;
import com.mttl.vlms.entity.Invoice;
import com.mttl.vlms.entity.InvoiceExample;
import com.mttl.vlms.entity.Item;
import com.mttl.vlms.entity.ItemExample;
import com.mttl.vlms.entity.ItemVehicle;
import com.mttl.vlms.entity.ItemVehicleExample;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.mapper.InvoiceMapper;
import com.mttl.vlms.mapper.ItemMapper;
import com.mttl.vlms.mapper.ItemVehicleMapper;
import com.mttl.vlms.operation.opr001.dto.BankAccountDto;
import com.mttl.vlms.operation.opr001.dto.InvoiceDto;
import com.mttl.vlms.operation.opr001.dto.InvoiceSearchReqDto;
import com.mttl.vlms.operation.opr001.dto.ItemDto;
import com.mttl.vlms.operation.opr001.dto.ItemVehicleDto;
import com.mttl.vlms.operation.opr001.dto.ItemVehicleSearchReqDto;
import com.mttl.vlms.operation.opr001.mapper.InvoiceCustomMapper;

/**
 * InvoiceDaoImpl
 * 
 * 
 *
 */
@Repository("InvoiceDao")
@Transactional(propagation = Propagation.REQUIRED)
public class InvoiceDaoImpl extends BasicDAO implements InvoiceDao {

	@Autowired
	private SelectMapper selectMapper;

	@Autowired
	private BeanConverter beanConverter;

	@Autowired
	private InvoiceMapper invoiceMapper;

	@Autowired
	private InvoiceCustomMapper invoiceCustomMapper;

	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private ItemVehicleMapper itemVehicleMapper;

	@Override
	@ApplyAspect
	public List<DealerSelectDto> getDealerList() throws DAOException {
		List<DealerSelectDto> dealerList;
		try {
			dealerList = selectMapper.getDealerList();
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return dealerList;
	}

	@Override
	@ApplyAspect
	public List<InvoiceDto> getInvoiceList(InvoiceSearchReqDto invoiceSearchReqDto, InvoiceDto invoiceSearchDto) throws DAOException {
		List<InvoiceDto> invoiceList;
		try {
			invoiceList = invoiceCustomMapper.getInvoiceList(invoiceSearchReqDto.getOffSet(), invoiceSearchReqDto.getLimit(), invoiceSearchReqDto.getSortField(),
					invoiceSearchReqDto.getSortOrder(), invoiceSearchReqDto.getFilters(), invoiceSearchDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return invoiceList;
	}

	@Override
	@ApplyAspect
	public Integer getInvoiceListCount(InvoiceSearchReqDto invoiceSearchReqDto) throws DAOException {
		Integer invoiceCount;
		try {
			invoiceCount = invoiceCustomMapper.getInvoiceListCount(invoiceSearchReqDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return invoiceCount;
	}

	@Override
	@ApplyAspect
	public List<ChargesSelectDto> getChargesList() throws DAOException {
		List<ChargesSelectDto> chargesList;
		try {
			chargesList = selectMapper.getChargesList();
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return chargesList;
	}

	@ApplyAspect
	@Override
	public List<BankAccountDto> getBankAccountList() throws DAOException {
		List<BankAccountDto> bankAccountList;
		try {
			bankAccountList = selectMapper.getBankAccountList();
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return bankAccountList;
	}

	@ApplyAspect
	@Override
	public List<TaskSelectDto> getTaskList() throws DAOException {
		List<TaskSelectDto> taskList;
		try {
			taskList = selectMapper.getTaskList();
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return taskList;
	}

	@ApplyAspect
	@Override
	public List<ItemVehicleDto> getItemVehicleList(ItemVehicleSearchReqDto invoiceVehicleSearchDto) throws DAOException {
		List<ItemVehicleDto> result;
		try {
			result = invoiceCustomMapper.getItemVehicleList(invoiceVehicleSearchDto);

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public void insertInvoice(InvoiceDto dto, Integer createdBy) throws DAOException {
		try {

			Date createdDate = Utils.getCurrentTime();

			Invoice invoice = beanConverter.convert(dto, Invoice.class);

			invoice.setCreatedDate(createdDate);
			invoice.setUpdatedDate(createdDate);
			invoice.setCreatedBy(createdBy);

			invoiceMapper.insertSelective(invoice);

			dto.setId(invoice.getId());

			// dto.setDealerId(invoice.getDealerId());

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}

	}

	@ApplyAspect
	@Override
	public void insertItem(List<ItemDto> itemList, Integer createdBy) throws DAOException {

		try {

			Date createdDate = Utils.getCurrentTime();

			for (ItemDto itemDto : itemList) {

				Item item = beanConverter.convert(itemDto, Item.class);

				item.setCreatedDate(createdDate);
				item.setUpdatedDate(createdDate);
				item.setCreatedBy(createdBy);
				item.setTaskFromDate(itemDto.getItemVehicleSearchReqDto().getTaskFromDate());
				item.setTaskToDate(itemDto.getItemVehicleSearchReqDto().getTaskToDate());
				item.setDealerId(itemDto.getItemVehicleSearchReqDto().getDealerId());

				itemMapper.insertSelective(item);
				for (ItemVehicleDto itemVehicleDto : itemDto.getItemVehicleDtoList()) {
					ItemVehicle itemVehicle = new ItemVehicle();
					itemVehicle.setItemId(item.getId());
					itemVehicle.setVehicleId(itemVehicleDto.getVehicleId());
					itemVehicle.setTaskedDate(itemVehicleDto.getTaskedDate());

					itemVehicleMapper.insertSelective(itemVehicle);
				}

			}

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}

	}

	@ApplyAspect
	@Override
	public void deleteItem() throws DAOException {

		try {
			ItemVehicleExample itemVehicleExample = new ItemVehicleExample();
			itemVehicleMapper.deleteByExample(itemVehicleExample);

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}

	}

	@ApplyAspect
	@Override
	public InvoiceDto getInvoiceByInvoiceId(Integer id) throws DAOException {
		InvoiceDto dto;
		try {
			dto = invoiceCustomMapper.getInvoiceByInvoiceId(id);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return dto;
	}

	@ApplyAspect
	@Override
	public List<ItemDto> getItemListByInvoiceId(Integer id) throws DAOException {

		List<ItemDto> result = new ArrayList<ItemDto>();

		try {

			ItemExample itemExample = new ItemExample();
			itemExample.createCriteria().andInvoiceIdEqualTo(id).andDeleteFlgEqualTo(false);

			List<Item> itemList = itemMapper.selectByExample(itemExample);

			for (Item item : itemList) {

				ItemDto itemDto = beanConverter.convert(item, ItemDto.class);

				ItemVehicleSearchReqDto itemVehicleSearchReqDto = new ItemVehicleSearchReqDto();
				itemVehicleSearchReqDto.setDealerId(item.getDealerId());
				itemVehicleSearchReqDto.setTaskFromDate(item.getTaskFromDate());
				itemVehicleSearchReqDto.setTaskToDate(item.getTaskToDate());
				itemVehicleSearchReqDto.setTaskId(item.getTaskId());
				itemDto.setItemVehicleSearchReqDto(itemVehicleSearchReqDto);

				List<ItemVehicleDto> itemVehicleDtoList = invoiceCustomMapper.getItemVehicleListByItemId(item.getId());
				itemDto.setItemVehicleDtoList(itemVehicleDtoList);

				result.add(itemDto);
			}

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public int updateInvoice(InvoiceDto invoiceDto, Integer updatedBy) throws DAOException {
		int count;

		try {

			Date updatedDate = Utils.getCurrentTime();

			Invoice invoice = beanConverter.convert(invoiceDto, Invoice.class);
			invoice.setId(invoiceDto.getId());
			invoice.setUpdatedDate(updatedDate);
			invoice.setUpdatedBy(updatedBy);

			InvoiceExample example = new InvoiceExample();
			example.createCriteria().andIdEqualTo(invoice.getId()).andDeleteFlgEqualTo(false);

			count = invoiceMapper.updateByExampleSelective(invoice, example);

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public void updateItem(List<ItemDto> itemList, Integer updatedBy) throws DAOException {

		try {

			for (ItemDto itemDto : itemList) {
				Date updatedDate = Utils.getCurrentTime();
				Item item = beanConverter.convert(itemDto, Item.class);
				item.setUpdatedDate(updatedDate);
				item.setUpdatedBy(updatedBy);
				ItemExample example = new ItemExample();
				example.createCriteria().andIdEqualTo(item.getId()).andDeleteFlgEqualTo(false);
				itemMapper.updateByExampleSelective(item, example);

			}

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public int deleteInvoice(InvoiceDto invoiceDto, Integer updatedBy) throws DAOException {
		int count;
		try {
			Date updatedDate = Utils.getCurrentTime();
			Invoice invoice = beanConverter.convert(invoiceDto, Invoice.class);
			invoice.setDeleteFlg(true);
			invoice.setUpdatedDate(updatedDate);

			InvoiceExample example = new InvoiceExample();
			example.createCriteria().andIdEqualTo(invoice.getId()).andDeleteFlgEqualTo(false);
			count = invoiceMapper.updateByExampleSelective(invoice, example);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public void deleteItem(List<ItemDto> itemList, Integer updatedBy) throws DAOException {

		try {

			for (ItemDto itemDto : itemList) {
				Date updatedDate = Utils.getCurrentTime();
				Item item = beanConverter.convert(itemDto, Item.class);
				item.setDeleteFlg(true);
				item.setUpdatedDate(updatedDate);
				ItemExample example = new ItemExample();
				example.createCriteria().andIdEqualTo(item.getId()).andDeleteFlgEqualTo(false);
				itemMapper.updateByExampleSelective(item, example);

			}

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public String getNextInvoiceNo() throws DAOException {

		String nextInvoiceNo = "";

		try {

			nextInvoiceNo = invoiceCustomMapper.getNextInvoiceNo();

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}

		return nextInvoiceNo;

	}

	@ApplyAspect
	@Override
	public DealerSelectDto getDealerById(Integer id) throws DAOException {
		DealerSelectDto dealerSelectDto = null;

		try {
			dealerSelectDto = invoiceCustomMapper.getDealerById(id);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return dealerSelectDto;
	}

}
