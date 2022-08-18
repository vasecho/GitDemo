import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	

	//once column is identified then scan entire testcase column to identify purchase test case row
	//after grabbing the whole purchase row scan it and take the all the data from it
	
	
	public ArrayList<String> getData(String testcaseName) throws IOException {
	ArrayList<String> a = new ArrayList<String>();
		
		//1. Create an object of  XSSFWorkbook class
		FileInputStream fis = new FileInputStream("C://Users//pc new//Documents//datademo2.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		//2. Get access to sheet (specific)
		int numberOfSheets = workbook.getNumberOfSheets();
		for(int i=0; i < numberOfSheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("testdata") ) {
				//3.Get access to all the rows in the sheet
				XSSFSheet sheet = workbook.getSheetAt(i);
				
				//Identify Testcases column by scanning the entire 1st row
				Iterator<Row> rows = sheet.iterator(); // sheet is collection of rows
				Row firstrow = rows.next();//in first row we have title of data ('test cases', 'Data1', ...) for example
				
				Iterator<Cell> ce = firstrow.cellIterator(); // row is collection of cells
			
				int k = 0;
				int column = 0;;
				while(ce.hasNext()) {
					
					Cell value = ce.next();
					
					if(value.getStringCellValue().equalsIgnoreCase("TestCases")) {
						//desired column
						column = k;
						//4. Access to the specific row from all the rows
					}//if
					k++;
				}//while
				System.out.println(column);
				
				//once column# is identified then scan entire testcase column to identify purchase test case row
				while(rows.hasNext()) {
					
					Row r = rows.next();
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {//idemo po redovima u koloni testcase
						
						//after grabbing the whole purchase row scan it and take the all the data from it
						//5.access to all the cells from the row of specific column
						Iterator<Cell> cv = r.cellIterator();
						 while(cv.hasNext()) {
							 
							 
							 //6.access the data from excel into array
							 Cell c = cv.next();
							 if(c.getCellType() == CellType.STRING) {
								 a.add(c.getStringCellValue());
							 }//if
							 else {
								 a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							 }
							 
							 
						 }//while
						
						
					}//if
					
				}//while
				
				
				
			} //if
			
		} // for
		workbook.close();
		if(a.isEmpty()) {
			System.out.println("there is no such column");
			return null;
		}
		else {
			return a;
		}
		
	}//getData()
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

	
		
	}//main

}//class
