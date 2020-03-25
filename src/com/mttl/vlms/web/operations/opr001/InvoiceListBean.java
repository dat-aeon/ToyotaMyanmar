package com.mttl.vlms.web.operations.opr001;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.LazyDataModel;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.operation.opr001.dto.InvoiceDto;
import com.mttl.vlms.operation.opr001.dto.InvoiceSearchReqDto;
import com.mttl.vlms.operation.opr001.dto.ItemDto;
import com.mttl.vlms.operation.opr001.service.InvoiceService;
import com.mttl.vlms.web.common.BaseBean;

import net.sf.jasperreports.engine.JRException;

/**
 * InvoiceListBean
 * 
 * 
 *
 */
@ManagedBean(name = "InvoiceListBean")
@ViewScoped
public class InvoiceListBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 711835142925201355L;

	@ManagedProperty(value = "#{InvoiceService}")
	private InvoiceService invoiceService;

	private Integer type;

	private Integer firstRowCount;

	private boolean paginatorVisible;

	private LazyDataModel<InvoiceDto> invoiceListLazyDataModel;

	private List<InvoiceDto> invoiceList;

	private InvoiceSearchReqDto invoiceSearchReqDto;

	private InvoiceDto invoiceSearchDto;

	private String exporterOutputPath;

	private List<ItemDto> itemDtoList;

	@PostConstruct
	public void init() {

		invoiceSearchReqDto = new InvoiceSearchReqDto();
		invoiceSearchDto = new InvoiceDto();
		invoiceList = new ArrayList<>();

		type = (Integer) getSessionParam(CommonConstants.INVOICE_TYPE);
		putSessionParam(CommonConstants.INVOICE_TYPE, type);

		searchInvoice();

	}

	public String callJasper(InvoiceDto invoiceDto) throws JRException, IOException {

		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmInvoiceList:invoiceList");
		firstRowCount = dataTable.getFirst();
		itemDtoList = invoiceService.getItemListByInvoiceId(invoiceDto.getId());
		invoiceDto.setItemList(itemDtoList);
		putSessionParam(CommonConstants.INVOICE_JASPER_REPORT, invoiceDto);
		putSessionParam(CommonConstants.INVOICE_JASPER_REPORT_ITEM, itemDtoList);
		return "invoiceReport";
	}

	/*
	 * public void callJasper(InvoiceDto invoiceDto) throws JRException,
	 * IOException { itemDtoList =
	 * invoiceService.getItemListByInvoiceId(invoiceDto.getId()); // String
	 * reportSrcFile = Utils.getSystemPath() + //
	 * "report-template/reportTesting.jasper"; // File reportFilePath = new
	 * File(reportSrcFile);
	 * 
	 * String reportSrcFile = Utils.getSystemPath() +
	 * "report-template/reportTesting.jrxml";
	 * 
	 * // First, compile jrxml file. JasperReport reportFilePath =
	 * JasperCompileManager.compileReport(reportSrcFile);
	 * 
	 * Collection<Object> invoiceDtoColl = new ArrayList<Object>();
	 * invoiceDtoColl.add(invoiceDto);
	 * 
	 * HashMap<String, Object> params = new HashMap<>(); params.put("itemList",
	 * itemDtoList);
	 * 
	 * JRBeanCollectionDataSource jrBeanCollectionDataSource = new
	 * JRBeanCollectionDataSource(invoiceDtoColl, false);
	 * 
	 * JasperPrint print = JasperFillManager.fillReport(reportFilePath, params,
	 * jrBeanCollectionDataSource);
	 * 
	 * File outDir = new File(Utils.getSystemPath() + "/temp"); outDir.mkdirs();
	 * 
	 * JRPdfExporter exporter = new JRPdfExporter();
	 * 
	 * ExporterInput exporterInput = new SimpleExporterInput(print);
	 * 
	 * exporter.setExporterInput(exporterInput);
	 * 
	 * OutputStreamExporterOutput exporterOutput = new
	 * SimpleOutputStreamExporterOutput(Utils.getSystemPath() +
	 * "/temp/invoiceReport.pdf");
	 * 
	 * exporter.setExporterOutput(exporterOutput);
	 * 
	 * SimplePdfExporterConfiguration configuration = new
	 * SimplePdfExporterConfiguration();
	 * exporter.setConfiguration(configuration); exporter.exportReport();
	 * exporterOutputPath = "/temp/invoiceReport.pdf";
	 * 
	 * System.out.print("Done!");
	 * 
	 * }
	 */

	public void searchInvoice() {

		if (invoiceSearchDto.getInvoiceNo() == "") {
			invoiceSearchDto.setInvoiceNo(null);
		}

		Integer invoiceCount = invoiceService.getInvoiceListCount(invoiceSearchReqDto);
		if (invoiceCount > 10) {
			paginatorVisible = true;
		}
		invoiceListLazyDataModel = new InvoiceListLazyModel(invoiceCount, invoiceSearchReqDto, invoiceService, invoiceSearchDto);

	}

	public void showMessage() {
		String messageCode = (String) getSessionParam(CommonConstants.MESSAGE_CODE);
		String messageParameter = (String) getSessionParam(CommonConstants.MESSAGE_PARAMETER);
		removeSessionParam(CommonConstants.MESSAGE_CODE, CommonConstants.MESSAGE_PARAMETER);
		if (messageCode != null && messageParameter != null) {
			addErrorMessage(null, messageCode, messageParameter);
		}
	}

	public String editInvoice(InvoiceDto invoiceDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmInvoiceList:invoiceList");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.INVOICE_DTO_EDIT, invoiceDto);
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		return "invoiceEdit";
	}

	public String deleteConfirmInvoice(InvoiceDto invoiceDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmInvoiceList:invoiceList");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.INVOICE_DTO_DELETE, invoiceDto);
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		return "invoiceDeleteConfirm";
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		int filterInt = getInteger(filterText);

		InvoiceDto invoice = (InvoiceDto) value;
		return invoice.getCustomerDealerName().toLowerCase().contains(filterText) || invoice.getInvoiceNo().toLowerCase().contains(filterText)
				|| invoice.getSubject().toLowerCase().contains(filterText);

	}

	private int getInteger(String string) {
		try {
			return Integer.valueOf(string);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public boolean isPaginatorVisible() {
		return paginatorVisible;
	}

	public Integer getFirstRowCount() {
		return firstRowCount;
	}

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	public LazyDataModel<InvoiceDto> getInvoiceListLazyDataModel() {
		return invoiceListLazyDataModel;
	}

	public void setInvoiceListLazyDataModel(LazyDataModel<InvoiceDto> invoiceListLazyDataModel) {
		this.invoiceListLazyDataModel = invoiceListLazyDataModel;
	}

	public List<InvoiceDto> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<InvoiceDto> invoiceList) {
		this.invoiceList = invoiceList;
	}

	public List<ItemDto> getItemDtoList() {
		return itemDtoList;
	}

	public void setItemDtoList(List<ItemDto> itemDtoList) {
		this.itemDtoList = itemDtoList;
	}

	public String getExporterOutputPath() {
		return exporterOutputPath;
	}

	public void setExporterOutputPath(String exporterOutputPath) {
		this.exporterOutputPath = exporterOutputPath;
	}

	public InvoiceSearchReqDto getInvoiceSearchReqDto() {
		return invoiceSearchReqDto;
	}

	public void setInvoiceSearchReqDto(InvoiceSearchReqDto invoiceSearchReqDto) {
		this.invoiceSearchReqDto = invoiceSearchReqDto;
	}

	public InvoiceDto getInvoiceSearchDto() {
		return invoiceSearchDto;
	}

	public void setInvoiceSearchDto(InvoiceDto invoiceSearchDto) {
		this.invoiceSearchDto = invoiceSearchDto;
	}

}
