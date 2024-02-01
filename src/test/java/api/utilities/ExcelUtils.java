package api.utilities;

import java.io.*;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet st;
	public static XSSFRow rw;
	public static XSSFCell cl;
	public static CellStyle style;
	String path;

	public ExcelUtils(String path) {
		this.path=path;
	}
	public  int getRowCount(String xlsheet) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		st = wb.getSheet(xlsheet);
		int rowCount = st.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}

	public  int getCellCount(String xlsheet, int rownum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		st = wb.getSheet(xlsheet);
		rw = st.getRow(rownum);
		int cellCount = rw.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
	}

	public String getCellData( String xlsheet, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		st = wb.getSheet(xlsheet);
		rw = st.getRow(rownum);
		cl = rw.getCell(colnum);
		String cellData;
		try {
			DataFormatter formatter = new DataFormatter();
			cellData = formatter.formatCellValue(cl);
			return cellData;
		} catch (Exception e) {
			cellData = "";
		}
		wb.close();
		fi.close();
		return cellData;

	}

	public  void setCellData( String xlsheet, int rownum, int colnum, String data)
			throws IOException {
		File xlfile = new File(path);
		if(!xlfile.exists()) {
			wb=new XSSFWorkbook();
			fo=new  FileOutputStream(path);
			wb.write(fo);
		}
		
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		
		if(wb.getSheetIndex(xlsheet)==-1)
			wb.createSheet(xlsheet);
		st = wb.getSheet(xlsheet);
		
		if(st.getRow(rownum)==null)
			st.createRow(rownum);
		rw = st.getRow(rownum);
		
		cl=rw.createCell(colnum);
//		cl = rw.getCell(colnum);
		cl.setCellValue(data);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();

	}

	public  void fillGreenColor( String xlsheet, int rownum, int colnum)
			throws IOException {

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		st = wb.getSheet(xlsheet);
		rw = st.getRow(rownum);
		cl = rw.getCell(colnum);

		style = wb.createCellStyle();

		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cl.setCellStyle(style);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();

	}

	public  void fillRedColor( String xlsheet, int rownum, int colnum)
			throws IOException {

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		st = wb.getSheet(xlsheet);
		rw = st.getRow(rownum);
		cl = rw.getCell(colnum);

		style = wb.createCellStyle();

		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cl.setCellStyle(style);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();

	}
}
