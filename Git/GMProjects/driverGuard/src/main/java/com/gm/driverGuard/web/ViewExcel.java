package com.gm.driverGuard.web;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.gm.driverGuard.util.NumberValidationUtils;

public class ViewExcel extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		List<DBObject> dbObjs = (List<DBObject>) model.get("dataList");
		String params = (String) model.get("params");
//		workbook = this.buildHSSFWorkbook(dbObjs, params);
		String dateStr = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		String filename = "ngidata" + dateStr + ".xls";// 设置下载时客户端Excel的名称
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + filename);
		OutputStream ouputStream = response.getOutputStream();
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

//	private HSSFWorkbook buildHSSFWorkbook(List<DBObject> dbObjs, String params) {
//		String[] columns = null;
//		if (params == null) {
//			columns = new String[0];
//		} else {
//			columns = params.split(",");
//		}
//		HSSFWorkbook workbook = new HSSFWorkbook();
//		HSSFSheet sheet = workbook.createSheet("ngiData");
//		// workbook.setSheetName(0,sheetName,HSSFWorkbook..ENCODING_UTF_16);
//		HSSFRow row = sheet.createRow((short) 0);
//		HSSFCell cell = null;
//		int nColumn = columns.length;
//		// index cell
//		cell = row.createCell(0);
//		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//		cell.setCellValue("index");
//
//		// index cell
//		cell = row.createCell(1);
//		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
//		cell.setCellValue(" upload date Time");
//
//		// 写入各个字段的名称
//		for (int i = 1; i <= nColumn; i++) {
//			cell = row.createCell((i + 1));
//			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//			cell.setCellValue(columns[i - 1]);
//		}
//
//		// 写入各条记录，每条记录对应Excel中的一行
//		for (int i = 0; i < dbObjs.size(); i++) {
//			int iRow = i + 1;
//			row = sheet.createRow((short) iRow);
//			// index
//			cell = row.createCell(0);
//			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//			cell.setCellValue(i + 1);
//			//
//			// index
//			cell = row.createCell(1);
//			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
//			DBObject dbObj = dbObjs.get(i);
//			Object o = dbObj.get("uploadTime");
//			String cellVal = "";
//			if (o != null) {
//				cellVal = new SimpleDateFormat("HH:mm:ss:SSS").format(new Date(Long.valueOf(o.toString())));
//			}
//			cell.setCellValue(cellVal);
//			for (int j = 1; j <= nColumn; j++) {
//				cell = row.createCell(j + 1);
//				DBObject oj = dbObjs.get(i);
//				Object obj = oj.get(columns[j - 1]);
//				String cellVal1 = "";
//				if (obj != null) {
//					cellVal1 = obj.toString();
//					if(NumberValidationUtils.isDecimal(cellVal1)||NumberValidationUtils.isWholeNumber(cellVal1)){
//						cell.setCellValue(Double.parseDouble(cellVal1));
//						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
//					}else{
//						cell.setCellValue(new HSSFRichTextString(cellVal1));
//					}
//				}else{
//					cell.setCellValue(new HSSFRichTextString(cellVal1));
//				}
//			}
//			iRow++;
//		}
//		return workbook;
//	}

}
