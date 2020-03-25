package com.mttl.vlms.web.operations.opr001;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.operation.opr001.dto.InvoiceDto;
import com.mttl.vlms.operation.opr001.dto.ItemDto;
import com.mttl.vlms.operation.opr001.dto.ItemVehicleDto;
import com.mttl.vlms.web.common.BaseBean;
import com.mttl.vlms.web.common.FileHandler;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@ManagedBean(name = "InvoiceJasperBean")
@ViewScoped
public class InvoiceJasperReport extends BaseBean implements Serializable {

	private static final long serialVersionUID = 711835142925201355L;

	@ManagedProperty(value = "#{jdbc_config}")
	private Properties jdbc_config;

	private String exporterOutputPath;

	private StreamedContent sourceFilePath;

	private InvoiceDto invoiceDto;

	private List<ItemDto> itemDtoList;

	private List<ItemVehicleDto> itemVehicleDtoList;

	HashMap<String, Object> objList = new HashMap<>();
	/* List<ItemDto> objList = new ArrayList<>(); */

	@PostConstruct
	public void init() {
		invoiceDto = (InvoiceDto) getSessionParam(CommonConstants.INVOICE_JASPER_REPORT);
		itemDtoList = (List<ItemDto>) getSessionParam(CommonConstants.INVOICE_JASPER_REPORT_ITEM);
		itemVehicleDtoList = new ArrayList<ItemVehicleDto>();

		itemVehicleDtoList = itemDtoList.get(0).getItemVehicleDtoList();
		System.out.println(itemDtoList.size());
		for (int i = 0; i < itemDtoList.size(); i++) {
			/* objList.add(itemDtoList.get(i).getItemVehicleDtoList()); */
			objList.put("objList", itemDtoList.get(i).getItemVehicleDtoList());
			System.out.println(objList.size());
		}

		/*
		 * for (Object itemList : objList) {
		 * 
		 * System.out.println(itemList.toString());
		 * System.out.println("========================"); }
		 */

		try {
			jasperReport();
		} catch (JRException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void jasperReport() throws JRException, IOException {
		List<JasperPrint> list = new ArrayList<JasperPrint>();
		String reportDesFile;
		// itemDtoList =
		// invoiceService.getItemListByInvoiceId(invoiceDto.getId());
		// String reportSrcFile = Utils.getSystemPath() +
		// "report-template/reportTesting.jasper";
		// File reportFilePath = new File(reportSrcFile);

		/*
		 * String reportSrcFile = Utils.getSystemPath() +
		 * "report-template/reportTesting.jrxml";
		 */

		Collection<Object> invoiceDtoColl = new ArrayList<Object>();
		invoiceDtoColl.add(invoiceDto);

		HashMap<String, Object> params = new HashMap<>();

		// params.put("itemList", itemDtoList);
		// params.put("subTotal", invoiceDto.getSubTotal());
		// params.put("taxAmount", invoiceDto.getTaxAmount());
		// params.put("grandTotal", invoiceDto.getGrandTotal());
		// params.put("currency", invoiceDto.getCurrency());
		// params.put("remark1", invoiceDto.getRemark1());
		// params.put("remark2", invoiceDto.getRemark2());

		/*
		 * System.out.println("DataList==" + objList.get(0).;
		 * System.out.println("DataList==" +
		 * itemVehicleDtoList.get(0).getModel());
		 * System.out.println("DataList==" +
		 * itemVehicleDtoList.get(0).getTaskedDate());
		 */
		/* System.out.println(objList.get(0).get(0)); */
		// params.put("itemVehicleList", itemVehicleDtoList);

		JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(invoiceDtoColl, false);
		if (invoiceDto.getTaxIncluded() == true) {
			reportDesFile = Utils.getSystemPath() + "report-template/reportTesting.jrxml";
		} else {
			reportDesFile = Utils.getSystemPath() + "report-template/reportTesting2.jrxml";
		}

		JasperReport reportDesPath = JasperCompileManager.compileReport(reportDesFile);
		JasperPrint print = JasperFillManager.fillReport(reportDesPath, params, jrBeanCollectionDataSource);
		list.add(print);

		System.out.println("first finish");

		JRBeanCollectionDataSource jrBeanCollectionDataSource1 = new JRBeanCollectionDataSource(invoiceDtoColl, false);
		String reportDesFile1 = Utils.getSystemPath() + "report-template/new_test_test_TEST.jrxml";
		JasperReport reportDesPath1 = JasperCompileManager.compileReport(reportDesFile1);
		JasperPrint print1 = JasperFillManager.fillReport(reportDesPath1, params, jrBeanCollectionDataSource1);
		list.add(print1);

		// JRBeanCollectionDataSource jrBeanCollectionDataSource1 = new
		// JRBeanCollectionDataSource(invoiceDtoColl, false);
		// String reportDesFile1 = Utils.getSystemPath() +
		// "report-template/new_test.jrxml";
		// JasperReport reportDesPath1 =
		// JasperCompileManager.compileReport(reportDesFile1);
		// JasperPrint print1 = JasperFillManager.fillReport(reportDesPath1,
		// params, jrBeanCollectionDataSource1);
		// list.add(print1);

		// JRBeanCollectionDataSource jrBeanCollectionDataSource1 = new
		// JRBeanCollectionDataSource(invoiceDtoColl, false);
		// String reportDesFile1 = Utils.getSystemPath() +
		// "report-template/ListTesting.jrxml";
		// JasperReport reportDesPath1 =
		// JasperCompileManager.compileReport(reportDesFile1);
		// JasperPrint print1 = JasperFillManager.fillReport(reportDesPath1,
		// params, jrBeanCollectionDataSource1);
		// list.add(print1);

		// First, compile jrxml file.
		/*
		 * for (int i = 0; i < 2; i++) { JasperReport reportDesPath =
		 * JasperCompileManager.compileReport(reportDesFile); JasperPrint print
		 * = JasperFillManager.fillReport(reportDesPath, params,
		 * jrBeanCollectionDataSource); list.add(print); reportDesFile = null;
		 * reportDesPath = null; print = null; reportDesFile =
		 * Utils.getSystemPath() + "report-template/ListTesting.jrxml"; }
		 */
		for (Object bean : list) {
			System.out.println(bean);
		}
		/*
		 * JasperReport reportFilePath =
		 * JasperCompileManager.compileReport(reportSrcFile);
		 */

		/*
		 * reportDesPath = null;
		 * 
		 * reportDesPath = JasperCompileManager.compileReport(reportSrcFile);
		 * 
		 * JasperPrint print1 = JasperFillManager.fillReport(reportDesPath,
		 * params, jrBeanCollectionDataSource);
		 * 
		 * list.add(print1);
		 */

		File outDir = new File(Utils.getSystemPath() + "/temp");
		outDir.mkdirs();

		JRPdfExporter exporter = new JRPdfExporter();

		// ExporterInput exporterInput = new SimpleExporterInput(print);
		/* list.add(print); */

		/*
		 * exporter.setExporterInput(SimpleExporterInput.getInstance(new
		 * ArrayList<JasperPrint>(list)));
		 */
		// exporter.setExporterInput(exporterInput);

		OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(Utils.getSystemPath() + "/temp/invoiceReport.pdf");
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		exporter.setConfiguration(configuration);
		exporter.setExporterInput(SimpleExporterInput.getInstance(list));
		exporter.setExporterOutput(exporterOutput);
		exporter.exportReport();
		exporterOutputPath = "/temp/invoiceReport.pdf";
		System.out.print("Done!");

	}

	public void pdfDownload() throws FileNotFoundException {
		File sourcePath = new File(FileHandler.getSystemPath() + "temp/invoiceReport.pdf");
		javax.faces.context.ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		sourceFilePath = new DefaultStreamedContent(new FileInputStream(sourcePath), externalContext.getMimeType(sourcePath.getName()), sourcePath.getName());

	}

	public Properties getJdbc_config() {
		return jdbc_config;
	}

	public void setJdbc_config(Properties jdbc_config) {
		this.jdbc_config = jdbc_config;
	}

	public String getExporterOutputPath() {
		return exporterOutputPath;
	}

	public void setExporterOutputPath(String exporterOutputPath) {
		this.exporterOutputPath = exporterOutputPath;
	}

	public List<ItemDto> getItemDtoList() {
		return itemDtoList;
	}

	public void setItemDtoList(List<ItemDto> itemDtoList) {
		this.itemDtoList = itemDtoList;
	}

	public StreamedContent getSourceFilePath() {
		return sourceFilePath;
	}

	public void setSourceFilePath(StreamedContent sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}

	public List<ItemVehicleDto> getItemVehicleDtoList() {
		return itemVehicleDtoList;
	}

	public void setItemVehicleDtoList(List<ItemVehicleDto> itemVehicleDtoList) {
		this.itemVehicleDtoList = itemVehicleDtoList;
	}

}
