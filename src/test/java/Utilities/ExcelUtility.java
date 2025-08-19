package Utilities;


	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;

	import org.apache.poi.ss.usermodel.*;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class ExcelUtility {
	    
	    private String filePath;
	    private FileInputStream fis;
	    private FileOutputStream fos;
	    private Workbook workbook;
	    private Sheet sheet;
	    private Row row;
	    private Cell cell;
	    
	    // Constructor
	    public ExcelUtility(String filePath) {
	        this.filePath = filePath;
	    }
	    
	    // 1. Get Row Count
	    public int getRowCount(String sheetName) throws IOException {
	        fis = new FileInputStream(filePath);
	        workbook = new XSSFWorkbook(fis);
	        sheet = workbook.getSheet(sheetName);
	        int rowCount = sheet.getLastRowNum();
	        workbook.close();
	        fis.close();
	        return rowCount;
	    }
	    
	    // 2. Get Cell Count in a Row
	    public int getCellCount(String sheetName, int rowNum) throws IOException {
	        fis = new FileInputStream(filePath);
	        workbook = new XSSFWorkbook(fis);
	        sheet = workbook.getSheet(sheetName);
	        row = sheet.getRow(rowNum);
	        int cellCount = row.getLastCellNum();
	        workbook.close();
	        fis.close();
	        return cellCount;
	    }
	    
	    // 3. Get Cell Data
	    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
	        fis = new FileInputStream(filePath);
	        workbook = new XSSFWorkbook(fis);
	        sheet = workbook.getSheet(sheetName);
	        row = sheet.getRow(rowNum);
	        cell = row.getCell(colNum);
	        
	        DataFormatter formatter = new DataFormatter();
	        String data;
	        try {
	            data = formatter.formatCellValue(cell);
	        } catch (Exception e) {
	            data = "";
	        }
	        
	        workbook.close();
	        fis.close();
	        return data;
	    }
	    
	    // 4. Set Cell Data
	    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
	        fis = new FileInputStream(filePath);
	        workbook = new XSSFWorkbook(fis);
	        sheet = workbook.getSheet(sheetName);
	        
	        row = sheet.getRow(rowNum);
	        if(row == null) row = sheet.createRow(rowNum);
	        
	        cell = row.getCell(colNum);
	        if(cell == null) cell = row.createCell(colNum);
	        
	        cell.setCellValue(data);
	        
	        fos = new FileOutputStream(filePath);
	        workbook.write(fos);
	        workbook.close();
	        fis.close();
	        fos.close();
	    }
	    
	    // 5. Fill Green Color
	    public void fillGreenColor(String sheetName, int rowNum, int colNum) throws IOException {
	        fis = new FileInputStream(filePath);
	        workbook = new XSSFWorkbook(fis);
	        sheet = workbook.getSheet(sheetName);
	        row = sheet.getRow(rowNum);
	        cell = row.getCell(colNum);
	        
	        CellStyle style = workbook.createCellStyle();
	        style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        cell.setCellStyle(style);
	        
	        fos = new FileOutputStream(filePath);
	        workbook.write(fos);
	        workbook.close();
	        fis.close();
	        fos.close();
	    }
	    
	    // 6. Fill Red Color
	    public void fillRedColor(String sheetName, int rowNum, int colNum) throws IOException {
	        fis = new FileInputStream(filePath);
	        workbook = new XSSFWorkbook(fis);
	        sheet = workbook.getSheet(sheetName);
	        row = sheet.getRow(rowNum);
	        cell = row.getCell(colNum);
	        
	        CellStyle style = workbook.createCellStyle();
	        style.setFillForegroundColor(IndexedColors.RED.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        cell.setCellStyle(style);
	        
	        fos = new FileOutputStream(filePath);
	        workbook.write(fos);
	        workbook.close();
	        fis.close();
	        fos.close();
	    }
	}

	
	

