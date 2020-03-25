package com.mttl.vlms.web.operations.opr004;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.common.selectdto.DriverSelectDto;
import com.mttl.vlms.common.selectdto.TaskSelectDto;
import com.mttl.vlms.common.selectdto.YardSelectDto;
import com.mttl.vlms.operation.opr002.service.VehicleInfoService;
import com.mttl.vlms.operation.opr004.dto.VehicleProcessInfoDto;
import com.mttl.vlms.operation.opr004.dto.VehicleProcessInfoSearchReqDto;
import com.mttl.vlms.operation.opr004.service.VehicleProcessInfoService;
import com.mttl.vlms.setting.stt016.dto.TaskDto;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;
import com.mttl.vlms.web.common.FileHandler;
import com.mttl.vlms.web.common.MyBean;
import com.mttl.vlms.web.common.MyBean.ProcessTypeList;

/**
 * VehicleInfoListBean
 * 
 * 
 *
 */
@ManagedBean(name = "VehicleProcessInfoListBean")
@ViewScoped
public class VehicleProcessInfoListBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = -6598961226154170581L;

	private VehicleProcessInfoDto vehicleProcessInfoSearchDto;
	private List<VehicleProcessInfoDto> vehicleProcessInfoList;
	private List<VehicleProcessInfoDto> detailVeiwList;
	private VehicleProcessInfoDto selectedVPID;

	private boolean paginatorVisible;
	/** for paging page */
	private Integer firstRowCount;

	/* start vehicle process info */
	private List<DriverSelectDto> driverList;

	private List<TaskSelectDto> taskList;

	private List<DealerSelectDto> dealerList;

	private List<YardSelectDto> yardList;

	private List<String> chassisNoList;

	private boolean bolExport;

	private Integer countBySearch;
	@ManagedProperty(value = "#{VehicleProcessInfoService}")
	private VehicleProcessInfoService vehicleProcessInfoService;

	@ManagedProperty(value = "#{VehicleInfoService}")
	private VehicleInfoService vehicleInfoService;

	TaskDto taskDto = new TaskDto();

	private Map<Integer, String> taskProcessTypeMap;

	private static String[] columns = { "Chassis No.", "Model", "Main Check", "Port of Discharge", "Yard Location", "Agent Name For RTA ", "Vehicle No", "RTA Date", "DLR Order",
			"TTAP (VRO)", "Deliver Route(Form)", "Deliver Route(To)", "Customer", "Location Status", "Location", "Storage Period Days" };

	private static String[] columns1 = { "Driver Name", "Process Type ", "Trailer Type", "Parking Slot", "Inspection Date", "Task", "Car Body", "Engine", "Interior",
			"Yard Inspector", "Status", "Remarks", "Evidence Image" };

	private List<StreamedContent> images;

	private List<String> imagesList;

	private StreamedContent filePath;

	private File fileName;

	private boolean fullAccessFlag;

	/*
	 * @Autowired private VehicleProcessInfoListLazyModel
	 * vehicleProcessInfoListLazyModel;
	 */
	private LazyDataModel<VehicleProcessInfoDto> vehicleProcessInfoListLazyDataModel;

	private VehicleProcessInfoSearchReqDto vehicleProcessInfoSearchReqDto;

	@ManagedProperty(value = "#{APP_CONFIG}")
	private Properties APP_CONFIG;

	@PostConstruct
	public void init() {
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		if (user.getRoleID() == CommonConstants.ADMIN_ROLE || user.getRoleID() == CommonConstants.MANAGER_ROLE) {
			fullAccessFlag = true;
		} else {
			fullAccessFlag = false;
		}
		bolExport = true;
		vehicleProcessInfoSearchReqDto = new VehicleProcessInfoSearchReqDto();
		vehicleProcessInfoList = new ArrayList<>();
		vehicleProcessInfoSearchDto = (VehicleProcessInfoDto) getSessionParam(CommonConstants.VEHICLE_PROCESS_INFO_SEARCH);
		if (vehicleProcessInfoSearchDto == null) {
			vehicleProcessInfoSearchDto = new VehicleProcessInfoDto();
			vehicleProcessInfoSearchDto.setProcessType(null);
			changeUpdate();
		} else {
			changeUpdate();
			searchVehicleInfo();
		}

		images = new ArrayList<StreamedContent>();
		firstRowCount = (Integer) getSessionParam(CommonConstants.FIRST_ROW_COUNT);
		if (firstRowCount == null) {
			firstRowCount = 0;
		}

		removeSessionParam(CommonConstants.FIRST_ROW_COUNT, CommonConstants.CHASSIS_NO, CommonConstants.MODEL, CommonConstants.COLOR, CommonConstants.PORT_OF_DISCHARGE,
				CommonConstants.VEHICLE_INFO_DTO_DELETE, CommonConstants.VEHICLE_INFO_DTO_EDIT);

		setTaskProcessTypeMap(setupTaskProcessTypeMap());
	}

	public void showPicture(VehicleProcessInfoDto vehicleProcessInfoDto) throws IOException {
		images = new ArrayList<StreamedContent>();
		String uploadPath = APP_CONFIG.getProperty("IMAGE_BASE_FILEPATH") + APP_CONFIG.getProperty("inspectVehicleImageFolder");
		String sourcePath = Utils.getSystemPath() + "/temp";
		Utils.createDirectory(uploadPath);
		imagesList = vehicleProcessInfoService.getImageList(vehicleProcessInfoDto.getInspectVehicleId());
		FileHandler.copyDirectory(sourcePath, uploadPath);
		if (imagesList.size() > 0) {
			for (int i = 0; i < imagesList.size(); i++) {
				String imagePath1 = "/" + imagesList.get(i);
				File sourceFile = new File(sourcePath + imagePath1);
				if (sourceFile.exists()) {
					DefaultStreamedContent evidenceImage = new DefaultStreamedContent(new FileInputStream(sourceFile), "image/jpeg", sourceFile.getName());
					images.add(evidenceImage);
				}
			}
		}
	}

	public void exportExcel() {

		detailViewInfo(vehicleProcessInfoSearchDto);
		// added
		if (countBySearch == null) {
			return;
		}

		Sheet sheet;
		Row row;

		try {

			File templateFile = new File(Utils.getSystemPath() + "report-template/VPI_Report_Template.xlsx");
			Date date = new Date();
			String strDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
			File exportFile = new File(Utils.getSystemPath() + "temp/VehicleProcessInformation" + strDate + ".xlsx");

			Utils.copyFile(templateFile, exportFile);

			FileInputStream fileInputStream = new FileInputStream(exportFile);
			Workbook workbook = new XSSFWorkbook(fileInputStream);
			int count = 1;
			final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmVehicleInfo:tblVehicleInfo");
			Map<String, Object> filters = dataTable.getFilters();
			SortOrder sortOrder = SortOrder.valueOf(dataTable.getSortOrder().toUpperCase());
			String sortField = null;

			double rowsPerPrint = 2.0;
			int totalRowCount = countBySearch;
			int until = (int) Math.ceil(totalRowCount / rowsPerPrint);
			int start = 0;

			VehicleProcessInfoListLazyModel lazyModel = new VehicleProcessInfoListLazyModel(1, null, vehicleProcessInfoService, vehicleProcessInfoSearchDto);

			for (int i = 0; i < until; i++) {

				start = (int) ((i * rowsPerPrint));

				List<VehicleProcessInfoDto> test = null;

				test = lazyModel.load(start, (int) rowsPerPrint, sortField, sortOrder, filters);

				for (VehicleProcessInfoDto vehicleProcessInfo1 : test) {

					sheet = workbook.cloneSheet(0);
					workbook.setSheetName(count, WorkbookUtil.createSafeSheetName(vehicleProcessInfo1.getChassisNo(), '_'));
					row = sheet.createRow(1);
					row.createCell(0).setCellValue(vehicleProcessInfo1.getChassisNo());
					row.createCell(1).setCellValue(vehicleProcessInfo1.getModel());
					row.createCell(2).setCellValue(vehicleProcessInfo1.getDate());// ??
					row.createCell(5).setCellValue(vehicleProcessInfo1.getAgentName());
					row.createCell(6).setCellValue(vehicleProcessInfo1.getVehicleLicenseNo());
					row.createCell(8).setCellValue(vehicleProcessInfo1.getOrderMonth());

					int rowNum1 = 7;
					List<VehicleProcessInfoDto> processInfoList = vehicleProcessInfoService.searchDetailView(vehicleProcessInfo1.getChassisNo(), taskDto.getProcessType());
					DateFormat formatter = new SimpleDateFormat("(dd/MM/yyyy) hh:mm");

					for (VehicleProcessInfoDto vehicleProcessInfo2 : processInfoList) {
						row = sheet.createRow(rowNum1++);
						row.createCell(0).setCellValue(vehicleProcessInfo2.getDriverName());
						row.createCell(1).setCellValue(taskProcessTypeMap.get(vehicleProcessInfo2.getProcessType()));
						row.createCell(2).setCellValue(vehicleProcessInfo2.getTrailerType());
						row.createCell(3).setCellValue(vehicleProcessInfo2.getYardName() + vehicleProcessInfo2.getColName());
						row.createCell(4).setCellValue(formatter.format(vehicleProcessInfo2.getInspectedDate()));
						row.createCell(5).setCellValue(vehicleProcessInfo2.getTaskName());
						if (vehicleProcessInfo2.isCarBody() == false) {
							row.createCell(6).setCellValue("\u2717");
						} else {
							row.createCell(6).setCellValue("\u2713");

						}
						if (vehicleProcessInfo2.isEngine() == false) {
							row.createCell(7).setCellValue("\u2717");
						} else {
							row.createCell(7).setCellValue("\u2713");

						}
						if (vehicleProcessInfo2.isInterior() == false) {
							row.createCell(8).setCellValue("\u2717");
						} else {
							row.createCell(8).setCellValue("\u2713");
						}

						row.createCell(9).setCellValue(vehicleProcessInfo2.getInspectorBy());
						row.createCell(11).setCellValue(vehicleProcessInfo2.getInspectRemark());
						if (vehicleProcessInfoService.getImageCountByInspectVehicleId(vehicleProcessInfo2.getInspectVehicleId()) == 0) {
							row.createCell(12).setCellValue("No");
						} else {
							row.createCell(12).setCellValue("Yes");

						}
					}

					count++;

					for (int j = 0; j < columns.length; j++) {
						sheet.autoSizeColumn(j);
					}

				}

			}

			workbook.removeSheetAt(0);
			fileInputStream.close();
			fileName = new File(FileHandler.getSystemPath() + "temp/VehicleProcessInformation" + strDate + ".xlsx");
			javax.faces.context.ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			filePath = new DefaultStreamedContent(new FileInputStream(fileName), externalContext.getMimeType(fileName.getName()), fileName.getName());

			FileOutputStream outputStream = new FileOutputStream(FileHandler.getSystemPath() + "temp/VehicleProcessInformation" + strDate + ".xlsx");
			workbook.write(outputStream);
			outputStream.close();
			workbook.close();
			// fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void exportExcel2() {

		detailViewInfo(vehicleProcessInfoSearchDto);
		// added
		if (countBySearch == null) {
			return;
		}

		Sheet sheet;
		Row row;

		try {

			File templateFile = new File(Utils.getSystemPath() + "report-template/VPI_Report_Template2.xlsx");
			Date date = new Date();
			String strDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
			File exportFile = new File(Utils.getSystemPath() + "temp/VehicleProcessInformation2" + strDate + ".xlsx");

			Utils.copyFile(templateFile, exportFile);

			FileInputStream fileInputStream = new FileInputStream(exportFile);
			Workbook workbook = new XSSFWorkbook(fileInputStream);
			int count = 1;
			final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmVehicleInfo:tblVehicleInfo");
			Map<String, Object> filters = dataTable.getFilters();
			SortOrder sortOrder = SortOrder.valueOf(dataTable.getSortOrder().toUpperCase());
			String sortField = null;

			double rowsPerPrint = 2.0;
			int totalRowCount = countBySearch;
			int until = (int) Math.ceil(totalRowCount / rowsPerPrint);
			int start = 0;

			VehicleProcessInfoListLazyModel lazyModel = new VehicleProcessInfoListLazyModel(1, null, vehicleProcessInfoService, vehicleProcessInfoSearchDto);

			for (int i = 0; i < until; i++) {

				start = (int) ((i * rowsPerPrint));

				List<VehicleProcessInfoDto> test = null;

				test = lazyModel.load(start, (int) rowsPerPrint, sortField, sortOrder, filters);

				for (VehicleProcessInfoDto vehicleProcessInfo1 : test) {

					sheet = workbook.cloneSheet(0);
					workbook.setSheetName(count, WorkbookUtil.createSafeSheetName(vehicleProcessInfo1.getChassisNo(), '_'));
					row = sheet.createRow(1);
					row.createCell(0).setCellValue(vehicleProcessInfo1.getChassisNo());
					row.createCell(1).setCellValue(vehicleProcessInfo1.getModel());
					row.createCell(2).setCellValue(vehicleProcessInfo1.getDate());// ??
					row.createCell(5).setCellValue(vehicleProcessInfo1.getAgentName());
					row.createCell(6).setCellValue(vehicleProcessInfo1.getVehicleLicenseNo());
					row.createCell(8).setCellValue(vehicleProcessInfo1.getOrderMonth());

					count++;

					for (int j = 0; j < columns.length; j++) {
						sheet.autoSizeColumn(j);
					}

				}

			}

			workbook.removeSheetAt(0);
			fileInputStream.close();
			fileName = new File(FileHandler.getSystemPath() + "temp/VehicleProcessInformation2" + strDate + ".xlsx");
			javax.faces.context.ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			filePath = new DefaultStreamedContent(new FileInputStream(fileName), externalContext.getMimeType(fileName.getName()), fileName.getName());

			FileOutputStream outputStream = new FileOutputStream(FileHandler.getSystemPath() + "temp/VehicleProcessInformation2" + strDate + ".xlsx");
			workbook.write(outputStream);
			outputStream.close();
			workbook.close();
			// fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void callExcel() {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmVehicleInfo:tblVehicleInfo");
		firstRowCount = dataTable.getFirst();
		exportExcel();

	}

	public void callExcel2() {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmVehicleInfo:tblVehicleInfo");
		firstRowCount = dataTable.getFirst();
		exportExcel2();

	}

	public void changeUpdate() {
		System.out.println(vehicleProcessInfoSearchDto.getProcessType() + "ProcessType#######");
		taskList = vehicleProcessInfoService.getTaskList(vehicleProcessInfoSearchDto.getProcessType());
		driverList = vehicleProcessInfoService.getDriverList(vehicleProcessInfoSearchDto.getProcessType());
		dealerList = vehicleProcessInfoService.getDealerList(vehicleProcessInfoSearchDto.getProcessType());
		yardList = vehicleProcessInfoService.getYardList(vehicleProcessInfoSearchDto.getProcessType());
	}

	public String clear() {
		PrimeFaces.current().resetInputs(":frmVehicleInfoUp:aplSearch");
		removeSessionParam(CommonConstants.VEHICLE_PROCESS_INFO_SEARCH);
		return "vehicleProcessInfoList";
	}

	public void showMessage() {
		String messageCode = (String) getSessionParam(CommonConstants.MESSAGE_CODE);
		String messageParameter = (String) getSessionParam(CommonConstants.MESSAGE_PARAMETER);
		removeSessionParam(CommonConstants.MESSAGE_CODE, CommonConstants.MESSAGE_PARAMETER);
		if (messageCode != null && messageParameter != null) {
			addErrorMessage(null, messageCode, messageParameter);
		}
	}

	public void searchVehicleInfo() {

		paginatorVisible = false;
		putSessionParam(CommonConstants.VEHICLE_PROCESS_INFO_SEARCH, this.vehicleProcessInfoSearchDto);
		vehicleProcessInfoListLazyDataModel = new VehicleProcessInfoListLazyModel(1, vehicleProcessInfoSearchReqDto, vehicleProcessInfoService, vehicleProcessInfoSearchDto);
		System.out.println(bolExport + "just testing");
		countBySearch = vehicleProcessInfoService.getCountBySearchTable(vehicleProcessInfoSearchDto);
		if (countBySearch == null) {
			bolExport = true;
		} else {
			bolExport = false;
		}
		if (vehicleProcessInfoList.size() > 10) {
			paginatorVisible = true;
		}
	}

	public String deleteConfirmVehicleInfo(VehicleProcessInfoDto vehicleProcessInfoDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmVehicleInfo:tblVehicleInfo");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		putSessionParam(CommonConstants.VEHICLE_INFO_DTO_DELETE, vehicleProcessInfoDto);
		putSessionParam(CommonConstants.CHASSIS_NO, this.vehicleProcessInfoSearchDto.getChassisNo());

		return "vehicleProcessInfoDeleteConfirm";
	}

	public void detailViewInfo(VehicleProcessInfoDto vehicleProcessInfoDto) {
		detailVeiwList = vehicleProcessInfoService.searchDetailView(vehicleProcessInfoDto.getChassisNo(), taskDto.getProcessType());
		selectedVPID = vehicleProcessInfoDto;

	}

	public void mainCheckViewInfo(VehicleProcessInfoDto vehicleProcessInfoDto) {
		detailVeiwList = vehicleProcessInfoService.searchDetailView(vehicleProcessInfoDto.getChassisNo(), 19);
		selectedVPID = vehicleProcessInfoDto;

	}

	public List<String> chassisNoList(String chassisNo, Integer ProcessType) {
		chassisNoList = vehicleProcessInfoService.getChassisNoList(chassisNo, ProcessType);
		return chassisNoList;
	}

	private Map<Integer, String> setupTaskProcessTypeMap() {
		Map<Integer, String> map = new HashMap<>();

		map = Arrays.stream(MyBean.ProcessTypeList.values()).collect(Collectors.toMap(ProcessTypeList::getId, e -> e.toString()));

		return map;
	}

	public boolean isPaginatorVisible() {
		return paginatorVisible;
	}

	public void setPaginatorVisible(boolean paginatorVisible) {
		this.paginatorVisible = paginatorVisible;
	}

	public List<VehicleProcessInfoDto> getVehicleProcessInfoList() {
		return vehicleProcessInfoList;
	}

	public void setVehicleProcessInfoList(List<VehicleProcessInfoDto> vehicleProcessInfoList) {
		this.vehicleProcessInfoList = vehicleProcessInfoList;
	}

	public void setVehicleInfoService(VehicleInfoService vehicleInfoService) {
		this.vehicleInfoService = vehicleInfoService;
	}

	public Integer getFirstRowCount() {
		return firstRowCount;
	}

	public TaskDto getTaskDto() {
		return taskDto;
	}

	public void setTaskDto(TaskDto taskDto) {
		this.taskDto = taskDto;
	}

	public List<DriverSelectDto> getDriverList() {
		return driverList;
	}

	public void setDriverList(List<DriverSelectDto> driverList) {
		this.driverList = driverList;
	}

	public List<TaskSelectDto> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TaskSelectDto> taskList) {
		this.taskList = taskList;
	}

	public List<DealerSelectDto> getDealerList() {
		return dealerList;
	}

	public void setDealerList(List<DealerSelectDto> dealerList) {
		this.dealerList = dealerList;
	}

	public List<YardSelectDto> getYardList() {
		return yardList;
	}

	public void setYardList(List<YardSelectDto> yardList) {
		this.yardList = yardList;
	}

	public VehicleProcessInfoService getVehicleProcessInfoService() {
		return vehicleProcessInfoService;
	}

	public void setVehicleProcessInfoService(VehicleProcessInfoService vehicleProcessInfoService) {
		this.vehicleProcessInfoService = vehicleProcessInfoService;
	}

	public VehicleInfoService getVehicleInfoService() {
		return vehicleInfoService;
	}

	public VehicleProcessInfoDto getVehicleProcessInfoSearchDto() {
		return vehicleProcessInfoSearchDto;
	}

	public void setVehicleProcessInfoSearchDto(VehicleProcessInfoDto vehicleProcessInfoSearchDto) {
		this.vehicleProcessInfoSearchDto = vehicleProcessInfoSearchDto;
	}

	public List<VehicleProcessInfoDto> getDetailVeiwList() {
		return detailVeiwList;
	}

	public void setDetailVeiwList(List<VehicleProcessInfoDto> detailVeiwList) {
		this.detailVeiwList = detailVeiwList;
	}

	public List<String> getChassisNoList() {
		return chassisNoList;
	}

	public void setChassisNoList(List<String> chassisNoList) {
		this.chassisNoList = chassisNoList;
	}

	public Map<Integer, String> getTaskProcessTypeMap() {
		return taskProcessTypeMap;
	}

	public void setTaskProcessTypeMap(Map<Integer, String> taskProcessTypeMap) {
		this.taskProcessTypeMap = taskProcessTypeMap;
	}

	public VehicleProcessInfoDto getSelectedVPID() {
		return selectedVPID;
	}

	public void setSelectedVPID(VehicleProcessInfoDto selectedVPID) {
		this.selectedVPID = selectedVPID;
	}

	public List<StreamedContent> getImages() {
		return images;
	}

	public void setImages(List<StreamedContent> images) {
		this.images = images;
	}

	public Properties getAPP_CONFIG() {
		return APP_CONFIG;
	}

	public void setAPP_CONFIG(Properties aPP_CONFIG) {
		APP_CONFIG = aPP_CONFIG;
	}

	public List<String> getImagesList() {
		return imagesList;
	}

	public void setImagesList(List<String> imagesList) {
		this.imagesList = imagesList;
	}

	public LazyDataModel<VehicleProcessInfoDto> getVehicleProcessInfoListLazyDataModel() {
		return vehicleProcessInfoListLazyDataModel;
	}

	public void setVehicleProcessInfoListLazyDataModel(LazyDataModel<VehicleProcessInfoDto> vehicleProcessInfoListLazyDataModel) {
		this.vehicleProcessInfoListLazyDataModel = vehicleProcessInfoListLazyDataModel;
	}

	public VehicleProcessInfoSearchReqDto getVehicleProcessInfoSearchReqDto() {
		return vehicleProcessInfoSearchReqDto;
	}

	public void setVehicleProcessInfoSearchReqDto(VehicleProcessInfoSearchReqDto vehicleProcessInfoSearchReqDto) {
		this.vehicleProcessInfoSearchReqDto = vehicleProcessInfoSearchReqDto;
	}

	public StreamedContent getFilePath() {
		return filePath;
	}

	public void setFilePath(StreamedContent filePath) {
		this.filePath = filePath;
	}

	public File getFileName() {
		return fileName;
	}

	public void setFileName(File fileName) {
		this.fileName = fileName;
	}

	public boolean isBolExport() {
		return bolExport;
	}

	public void setBolExport(boolean bolExport) {
		this.bolExport = bolExport;
	}

	public boolean isFullAccessFlag() {
		return fullAccessFlag;
	}

	public void setFullAccessFlag(boolean fullAccessFlag) {
		this.fullAccessFlag = fullAccessFlag;
	}

}