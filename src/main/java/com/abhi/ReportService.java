package com.abhi;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

	@Autowired
	private BookRepo bookRepo;
	
	public void generateExcel(HttpServletResponse response) throws IOException {
		
		List<Book> books = bookRepo.findAll();
		
		HSSFWorkbook workbook = new HSSFWorkbook(); 
		HSSFSheet sheet = workbook.createSheet("Books Info");
		HSSFRow row = sheet.createRow(0);
		
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("NAME");
		row.createCell(2).setCellValue("PRICE");
		
		int dataRowIndex = 1;
		
		for(Book book : books) {
			
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(book.getBookId());
			dataRow.createCell(1).setCellValue(book.getBookName());
			dataRow.createCell(2).setCellValue(book.getBookPrice());
			dataRowIndex++;
		}
		
		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();
		
	}
}
