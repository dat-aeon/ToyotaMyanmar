package com.mttl.vlms.web.setting.stt015;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.setting.stt015.dto.UploadVehicleFileDto;
import com.mttl.vlms.setting.stt015.service.UploadVehicleFileService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * UploadVehicleFileInputBean
 * 
 * 
 */
@ManagedBean(name = "UploadVehicleFileInputBean")
@ViewScoped
public class UploadVehicleFileInputBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -5038234829522378054L;

	// private String tempFileDir =
	// "D://toyota_dev_env//workspace//ToyotaMyanmar//WebContent/temp//";
	File tempFileDir = new File(Utils.getSystemPath() + "temp//");

	// private String templateDir =
	// "D://toyota_dev_env//workspace//ToyotaMyanmar//WebContent/report-template//";
	File templateDir = new File(Utils.getSystemPath() + "report-template//Free_Template.xlsx");

	@ManagedProperty(value = "#{UploadVehicleFileService}")
	private UploadVehicleFileService uploadVehicleFileService;

	private UploadVehicleFileDto uploadVehicleFileDto;

	private List<UploadVehicleFileDto> uploadVehicleFileDtoList;

	private UploadedFile file;

	private User loginUser;

	private int errorChek;

	private boolean dFlag;

	private boolean buttonFlag = true;

	private StreamedContent dFile;

	private int cellCount;

	@PostConstruct
	public void init() {
		dFlag = true;
		uploadVehicleFileDto = new UploadVehicleFileDto();
		loginUser = (User) getSessionParam(CommonConstants.LOGIN_USER);
	}

	public void reset() {
		dFlag = true;
		PrimeFaces.current().resetInputs("frmUploadVehicleFile");
		uploadVehicleFileDtoList = new ArrayList<>();
	}

	public void uploadListener() {
		System.out.println("Hello I'm Listener");
		if (file.getFileName().isEmpty() == true) {
			buttonFlag = false;
		} else if (!(file.getFileName().endsWith(".xlsx"))) {
			buttonFlag = false;

		} else if (file.getFileName().endsWith(".xlsx")) {
			buttonFlag = true;
		}
	}

	public void upload() throws IOException {

		dFlag = true;

		errorChek = 0;

		int insertCount = 0;

		int updateCount = 0;

		int ProcessFailOrSuccess = 0;

		if (file.getFileName().isEmpty() == true) {
			addErrorMessage("File required.");
		} else if (!(file.getFileName().endsWith(".xlsx"))) {
			addErrorMessage("File type wrong.");
		} else if (file.getFileName().endsWith(".xlsx")) {
			buttonFlag = true;
			String tempName = (String) getSessionParam(CommonConstants.FILE_NAME);
			File oldFile = new File(tempFileDir + tempName);
			if (tempName != null) {
				if (oldFile.delete()) {
					System.out.println("Delete temp File immediately");
				} else {
					oldFile.deleteOnExit();
					System.out.println("Forced Deleted!!!!");
				}

			}
			removeSessionParam(CommonConstants.FILE_NAME);
			putSessionParam(CommonConstants.FILE_NAME, file.getFileName());
			uploadVehicleFileDtoList = new ArrayList<UploadVehicleFileDto>();

			try {

				InputStream ip = file.getInputstream();

				XSSFWorkbook wb = new XSSFWorkbook(ip);

				Sheet sheet = wb.getSheetAt(0);

				Iterator<Row> itr = sheet.iterator();

				while (itr.hasNext()) {

					Row row = itr.next();
					if (isRowEmpty(row) == false) {

						if (row.getRowNum() >= 6) {

							uploadVehicleFileDto = new UploadVehicleFileDto();
							uploadVehicleFileDto = readAndModifyRow(row, wb);

							if (uploadVehicleFileDto.getDlrName() != null && uploadVehicleFileDto.getDischargePortId() != null) {
								ProcessFailOrSuccess = uploadVehicleFileService.insertUploadVehicleFile(uploadVehicleFileDto, loginUser.getUserID());

								if (ProcessFailOrSuccess == 0) {
									errorChek++;
								} else {
									uploadVehicleFileDto.setRowNumToDel(row.getRowNum());
								}

								uploadVehicleFileDtoList.add(uploadVehicleFileDto);

								if (uploadVehicleFileDto.getProcType() == "insert") {

									insertCount++;

								} else if (uploadVehicleFileDto.getProcType() == "update") {

									updateCount++;

								}

							} else {
								errorChek++;
							}
						}

					}

				}
				if (errorChek > 0) {
					dFlag = false;
				}

				removeSuccessRow(sheet);

				FileOutputStream fos = new FileOutputStream(new File(tempFileDir + file.getFileName()));
				wb.write(fos);

				fos.close();

				wb.close();

				if (insertCount != 0) {
					addInfoMessage(null, "MSG_CMN_041", insertCount);
				}
				if (updateCount != 0) {
					addInfoMessage(null, "MSG_CMN_042", updateCount);
				}
				if (errorChek != 0) {
					addErrorMessage(null, "MSG_CMN_043", errorChek);
				}

			} catch (Exception e) {

				e.printStackTrace();
			}

		}
	}

	public StreamedContent download() throws IOException {

		String tempName = (String) getSessionParam(CommonConstants.FILE_NAME);
		File tempFile = new File(tempFileDir + tempName);

		try {

			FileInputStream fileInput = new FileInputStream(new File(tempFileDir + tempName));
			dFile = new DefaultStreamedContent(fileInput, new MimetypesFileTypeMap().getContentType(tempFile.getAbsoluteFile()), tempName);
			addInfoMessage(null, "MSG_CMN_044", tempFile.getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			addErrorMessage(null, "MSG_CMN_045", tempFile.getName());
		}
		// tempFile.delete();

		return dFile;
	}

	public StreamedContent downloadTemp() {

		String tempFreeName = "Free_Template.xlsx";

		// File tempFreeFile = new File(templateDir);

		try {
			FileInputStream inputStream = new FileInputStream(templateDir);
			dFile = new DefaultStreamedContent(inputStream, new MimetypesFileTypeMap().getContentType(templateDir.getAbsoluteFile()), tempFreeName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// templateDir.delete();
		return dFile;
	}

	public boolean getCellType(Row row, CellType cellType1, CellType cellType2) {
		boolean chk = false;
		CellType type = row.getCell(cellCount).getCellTypeEnum();
		System.out.println(type);
		cellCount++;
		if (cellType1 != null) {
			if (type == cellType1) {
				chk = true;
			}
		}
		if (cellType2 != null) {
			if (type == cellType2) {
				chk = true;
			}
		}

		System.out.println(chk);
		return chk;

	}

	private UploadVehicleFileDto readAndModifyRow(Row row, Workbook workbook) {

		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellCount = 1;
		if (getCellType(row, CellType.NUMERIC, CellType.BLANK)) {
			Cell cell = row.getCell(1);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason("Model must be String.");
		} else {

			uploadVehicleFileDto.setModel(row.getCell(1).toString());
		}
		if (getCellType(row, CellType.NUMERIC, CellType.BLANK)) {
			Cell cell = row.getCell(2);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Chassis No must be String.");
		} else {

			uploadVehicleFileDto.setChassisNo(row.getCell(2).toString());
		}
		if (getCellType(row, CellType.NUMERIC, CellType.BLANK)) {
			Cell cell = row.getCell(3);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Color must be String.");
		} else {

			uploadVehicleFileDto.setColor(row.getCell(3).toString());
		}
		if (getCellType(row, CellType.NUMERIC, CellType.BLANK)) {
			Cell cell = row.getCell(4);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Color Code must be String.");
		} else {

			uploadVehicleFileDto.setColorCode(row.getCell(4).toString());
		}
		if (getCellType(row, CellType.NUMERIC, CellType.BLANK)) {
			Cell cell = row.getCell(5);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Discharge PortId must be String.");
		} else {
			uploadVehicleFileDto.setDischargePortId(uploadVehicleFileService.getPortOfDischargeId(row.getCell(5).toString()));
			if (uploadVehicleFileDto.getDischargePortId() == null) {

				Cell cell = row.getCell(5);
				cell.setCellStyle(style);
				uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Discharge Port Id not exist.");

			}
		}

		if (getCellType(row, CellType.STRING, null))

		{
			Cell cell = row.getCell(6);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Discharge Port Plan Date must be Date(dd/mm/yyyy).");
		} else {

			uploadVehicleFileDto.setDischargePortPlanDate(row.getCell(6).getDateCellValue());
		}

		if (getCellType(row, CellType.STRING, null)) {
			Cell cell = row.getCell(7);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Discharge Port Actual Date must be Date(dd/mm/yyyy).");
		} else {
			uploadVehicleFileDto.setDischargePortActualDate(row.getCell(7).getDateCellValue());
		}

		if (getCellType(row, CellType.STRING, null)) {
			Cell cell = row.getCell(8);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Etd Origin must be Date(dd/mm/yyyy).");
		} else {

			uploadVehicleFileDto.setEtdOrigin(row.getCell(8).getDateCellValue());
		}

		if (getCellType(row, CellType.STRING, null)) {
			Cell cell = row.getCell(9);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "EtdEstimate Date must be Date(dd/mm/yyyy).");
		} else {

			uploadVehicleFileDto.setEtdEstimateDate(row.getCell(9).getDateCellValue());
		}

		if (getCellType(row, CellType.STRING, null)) {
			Cell cell = row.getCell(10);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "EtdSin must be Date(dd/mm/yyyy).");
		} else {

			uploadVehicleFileDto.setEtdSin(row.getCell(10).getDateCellValue());
		}

		if (getCellType(row, CellType.STRING, null)) {
			Cell cell = row.getCell(11);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "ETA must be Date(dd/mm/yyyy).");
		} else {

			uploadVehicleFileDto.setEta(row.getCell(11).getDateCellValue());
		}

		if (getCellType(row, CellType.STRING, null)) {
			Cell cell = row.getCell(12);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "VesselBerthingDate value must be String.");
		} else {

			uploadVehicleFileDto.setVesselBerthingDate(row.getCell(12).getDateCellValue());
		}

		if (getCellType(row, CellType.NUMERIC, null)) {
			Cell cell = row.getCell(13);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Cell value must be String.");
		} else {
			uploadVehicleFileDto.setDlrName(uploadVehicleFileService.getDealerId(row.getCell(13).toString()));
			if (uploadVehicleFileDto.getDlrName() == null) {

				Cell cell = row.getCell(13);
				cell.setCellStyle(style);

				uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "dealername not exist.");

			}
		}

		if (getCellType(row, CellType.NUMERIC, null)) {
			Cell cell = row.getCell(14);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Consignee On Bl must be String.");
		} else {

			uploadVehicleFileDto.setConsigneeOnBl(row.getCell(14).toString());
		}

		if (getCellType(row, CellType.STRING, null)) {
			Cell cell = row.getCell(15);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Order Month must be Date(dd/mm/yyyy).");
		} else {

			uploadVehicleFileDto.setOrderMonth(row.getCell(15).getDateCellValue());
		}

		if (getCellType(row, CellType.NUMERIC, null)) {
			Cell cell = row.getCell(16);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "OrderBy must be String.");
		} else {

			uploadVehicleFileDto.setOrderBy(row.getCell(16).toString());
		}

		if (getCellType(row, CellType.NUMERIC, null)) {
			Cell cell = row.getCell(17);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "First Vessel must be String.");
		} else {

			uploadVehicleFileDto.setFirstVessel(row.getCell(17).toString());
		}

		if (getCellType(row, CellType.NUMERIC, null)) {
			Cell cell = row.getCell(18);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "SecondVessel must be String.");
		} else {

			uploadVehicleFileDto.setSecondVessel(row.getCell(18).toString());
		}

		if (getCellType(row, CellType.STRING, null)) {
			Cell cell = row.getCell(19);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "ProMonth must be String.");
		} else {

			uploadVehicleFileDto.setProdMth(row.getCell(19).getDateCellValue());
		}

		if (getCellType(row, CellType.NUMERIC, null)) {
			Cell cell = row.getCell(20);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Agent Name must be String.");
		} else {

			uploadVehicleFileDto.setAgentName(row.getCell(20).toString());
		}

		if (getCellType(row, CellType.STRING, null)) {
			Cell cell = row.getCell(21);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Carried Out Units From Port Date must be Date(dd/mm/yyyy).");
		} else {

			uploadVehicleFileDto.setCarriedOutUnitsFromPortDate(row.getCell(21).getDateCellValue());
		}

		if (getCellType(row, CellType.NUMERIC, null)) {
			Cell cell = row.getCell(22);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Carried Out Units From port remark must be String.");
		} else {

			uploadVehicleFileDto.setCarriedOutUnitsFromPortRemark(row.getCell(22).toString());
		}

		if (getCellType(row, CellType.NUMERIC, null)) {
			Cell cell = row.getCell(23);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Cell value must be String.");
		} else {

			uploadVehicleFileDto.setStockOf(row.getCell(23).toString());
		}

		if (getCellType(row, CellType.STRING, null)) {
			Cell cell = row.getCell(24);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "StockDate must be Date(dd/mm/yyyy).");
		} else {

			uploadVehicleFileDto.setStockDate(row.getCell(24).getDateCellValue());
		}

		if (getCellType(row, CellType.STRING, null)) {
			Cell cell = row.getCell(25);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Purchase Permit Date must be Date(dd/mm/yyyy).");
		} else {

			uploadVehicleFileDto.setPurchasePermitDate(row.getCell(25).getDateCellValue());
		}

		if (getCellType(row, CellType.NUMERIC, null)) {
			Cell cell = row.getCell(26);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Audio Install Require value must be String.");
		} else {

			if (row.getCell(26).getStringCellValue() == "Yes") {
				uploadVehicleFileDto.setAudioInstallRequired(Boolean.valueOf("True"));
			} else if (row.getCell(26).getStringCellValue() == "No") {
				uploadVehicleFileDto.setAudioInstallRequired(Boolean.valueOf("False"));
			}
		}
		if (getCellType(row, CellType.STRING, null)) {
			Cell cell = row.getCell(27);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Audio Mail Date value must be Date(dd/mm/yyyy).");
		} else {

			uploadVehicleFileDto.setAudioMaileDate(row.getCell(27).getDateCellValue());
		}

		if (getCellType(row, CellType.STRING, null)) {
			Cell cell = row.getCell(28);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Audio Install Date value must be Date(dd/mm/yyyy).");
		} else {

			uploadVehicleFileDto.setAudioInstallDate(row.getCell(28).getDateCellValue());
		}

		if (getCellType(row, CellType.NUMERIC, null)) {
			Cell cell = row.getCell(29);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Delivered Require value must be String(Yes Or No).");
		} else {

			if (row.getCell(29).getStringCellValue() == "Yes" || row.getCell(29).getStringCellValue() == "YES") {
				uploadVehicleFileDto.setDelivered(Boolean.valueOf("True"));
			} else if (row.getCell(29).getStringCellValue() == "No" || row.getCell(29).getStringCellValue() == "NO") {
				uploadVehicleFileDto.setDelivered(Boolean.valueOf("False"));
			}
		}

		if (getCellType(row, CellType.NUMERIC, null)) {
			Cell cell = row.getCell(30);
			cell.setCellStyle(style);

			uploadVehicleFileDto.setFailedReason(uploadVehicleFileDto.getFailedReason() + "Remark value must be String.");
		} else {

			uploadVehicleFileDto.setRemark(row.getCell(30).toString());
		}

		Cell reason = row.createCell(31);
		reason.setCellValue(uploadVehicleFileDto.getFailedReason());

		return uploadVehicleFileDto;

	}

	private void removeSuccessRow(Sheet sheet) {

		int nextRow = 0;
		for (UploadVehicleFileDto dto : uploadVehicleFileDtoList) {

			int lastRowNum = sheet.getLastRowNum();

			if (dto.getRowNumToDel() >= 0 && dto.getRowNumToDel() < lastRowNum) {
				sheet.shiftRows((dto.getRowNumToDel() + 1) - nextRow, lastRowNum, -1);
				nextRow++;

			}
			if (dto.getRowNumToDel() == lastRowNum) {
				Row removingRow = sheet.getRow(dto.getRowNumToDel());
				if (removingRow != null) {
					sheet.removeRow(removingRow);
				}
			}

		}
	}

	private static boolean isRowEmpty(Row row) {
		if (row == null) {
			return true;
		}
		if (row.getLastCellNum() <= 0) {
			return true;
		}
		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
				return false;
		}
		return true;
	}

	public UploadVehicleFileService getUploadVehicleFileService() {
		return uploadVehicleFileService;
	}

	public void setUploadVehicleFileService(UploadVehicleFileService uploadVehicleFileService) {
		this.uploadVehicleFileService = uploadVehicleFileService;
	}

	public UploadVehicleFileDto getUploadVehicleFileDto() {
		return uploadVehicleFileDto;
	}

	public void setUploadVehicleFileDto(UploadVehicleFileDto uploadVehicleFileDto) {
		this.uploadVehicleFileDto = uploadVehicleFileDto;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<UploadVehicleFileDto> getUploadVehicleFileDtoList() {
		return uploadVehicleFileDtoList;
	}

	public void setUploadVehicleFileDtoList(List<UploadVehicleFileDto> uploadVehicleFileDtoList) {
		this.uploadVehicleFileDtoList = uploadVehicleFileDtoList;
	}

	public int getErrorChek() {
		return errorChek;
	}

	public void setErrorChek(int errorChek) {
		this.errorChek = errorChek;
	}

	public boolean isdFlag() {
		return dFlag;
	}

	public void setdFlag(boolean dFlag) {
		this.dFlag = dFlag;
	}

	public StreamedContent getdFile() {
		return dFile;
	}

	public void setdFile(StreamedContent dFile) {
		this.dFile = dFile;
	}

	public boolean isButtonFlag() {
		return buttonFlag;
	}

	public void setButtonFlag(boolean buttonFlag) {
		this.buttonFlag = buttonFlag;
	}

}
