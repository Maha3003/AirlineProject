package org.maven.Airline;




import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelWrite {
public static void printTotalCost(String totalPrice) throws IOException
{
	FileOutputStream fs=new FileOutputStream("C:\\Users\\Maha v\\Desktop\\Excel\\ExcelWrite.xlsx");
	Workbook w=new XSSFWorkbook();
	Sheet s=w.createSheet("PalAirlines");
	Row r = s.createRow(0);
	Cell productCell = r.createCell(0);
	productCell.setCellValue("Total Fare");
	Row printRow = s.createRow(1);
	Cell printCell = printRow.createCell(0);
	printCell.setCellValue(totalPrice);
	w.write(fs);
	
}
}

