package com.acies.utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excelutil {
	
	
	
	/*
	 *
	 * Try to Get the Formulae cell value evaluator (Yet to Implement)
	 *  
	 */
	

	public static FileInputStream fis;
	public static File file;
	public static Workbook workbook;
	public static Sheet sheet;
	public static Row row;
	public static Cell cell;

	public void setPath(String Excelpath, String sheetName) {

		if (fis == null) {
			try {
				file = new File(Excelpath);
				fis = new FileInputStream(file);
				workbook = WorkbookFactory.create(fis);
				sheet = workbook.getSheet(sheetName);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getCause());
			}
		}
	}
	
	public int getNumberofSheets(){
		
		int NumberofSheets = workbook.getNumberOfSheets();
		System.out.println("Number of Sheets in Workbook_ "+NumberofSheets);
		
		return NumberofSheets;
	}
	
	public int getRowCount(String sheetName) {

		int index = workbook.getSheetIndex(sheetName);

		if (index == -1) {

			return 0;
		} else {

			int rowcount = sheet.getPhysicalNumberOfRows();

			System.out.println("Total Number of Rows _" + rowcount);
			return rowcount;

		}

	}

	public int getColCount(String sheetName) {

		int index = workbook.getSheetIndex(sheetName);

		if (index == -1) {

			return 0;
		} else {

			int Colcount = sheet.getRow(0).getLastCellNum();

			System.out.println("Total Number of Columns _" + Colcount);

			return Colcount;

		}

	}
	
	public String getCellData(int rownum, int colnum) throws Exception{
	    try{
	       cell = sheet.getRow(rownum).getCell(colnum);
	        String CellData = null;
	        switch (cell.getCellType()){
	            case STRING:
	                CellData = cell.getStringCellValue();
	                break;
	            case NUMERIC:
	                if (DateUtil.isCellDateFormatted(cell))
	                {
	                    CellData = String.valueOf(cell.getDateCellValue());
	                }
	                else
	                {
	                    CellData = String.valueOf((long)cell.getNumericCellValue());
	                }
	                break;
	            case BOOLEAN:
	                CellData = Boolean.toString(cell.getBooleanCellValue());
	                break;
	                
	            case FORMULA:
	            	
	            	
	            	
	            	
			case BLANK:
	                CellData = "";
	                break;
			case ERROR:
				break;
			case _NONE:
				break;
			default:
				break;
	        }
	        return CellData;
	    }catch (Exception e){
	        return"";
	    }
	}

	
}
