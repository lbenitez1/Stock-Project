package application;
//import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;
//import java.util.Scanner;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
//Class stock data meant to pull information from observer
public class StockDataSubject {
	public static Row row;
	public static Cell cell;
	public static Sheet sheet;
	public static String symbol;
	public double price, prevPrice;
	//write to the excel sheet
	private InputStream inp;
	private Workbook wb;
	private FileOutputStream fileOut;
	public StockDataSubject() throws EncryptedDocumentException, InvalidFormatException, IOException {
		// TODO Auto-generated method stub
		inp = new FileInputStream("/Users/leonardobenitez/Desktop/eclipse-stock-workspace/Stock/src/application/stocksheet.xlsx");
		
		wb = WorkbookFactory.create(inp);
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		
//		Workbook wb = new XSSFWorkbook();
//		CreationHelper createHelper = wb.getCreationHelper();
//		Sheet sheet = wb.createSheet("New Sheet");
		
		//pull data from the yahoo finance site
		String[] symbols = new String[]{"PLKI", "GOOGL", "COKE", "STZ", "FB", "AAPL", "NKE", "MMM", "HSY", "WFM"};
		Map<String, Stock> stocks = YahooFinance.get(symbols);
		
		BigDecimal popeyesPrice = getPrice("PLKI", stocks);
		BigDecimal popeyesPrevPrice = getPrevPrice("PLKI", stocks);
		BigDecimal googlePrice = getPrice("GOOGL", stocks);
		BigDecimal googlePrevPrice = getPrevPrice("GOOGL", stocks);
		BigDecimal cokePrice = getPrice("COKE", stocks);
		BigDecimal cokePrevPrice = getPrevPrice("COKE", stocks);
		BigDecimal coronaPrice = getPrice("STZ", stocks);
		BigDecimal coronaPrevPrice = getPrevPrice("STZ", stocks);
		BigDecimal facebookPrice = getPrice("FB", stocks);
		BigDecimal facebookPrevPrice = getPrevPrice("FB", stocks);
		BigDecimal applePrice = getPrice("AAPL", stocks);
		BigDecimal applePrevPrice = getPrevPrice("AAPL", stocks);
		BigDecimal nikePrice = getPrice("NKE", stocks);
		BigDecimal nikePrevPrice = getPrevPrice("NKE", stocks);
		BigDecimal threemPrice = getPrice("MMM", stocks);
		BigDecimal threemPrevPrice = getPrevPrice("MMM", stocks);
		BigDecimal hersheyPrice = getPrice("HSY", stocks);
		BigDecimal hersheyPrevPrice = getPrevPrice("HSY", stocks);
		BigDecimal wholeFoodsPrice = getPrice("WFM", stocks);
		BigDecimal wholeFoodsPrevPrice = getPrevPrice("WFM", stocks);
		/*
		System.out.println("Here are the current stock prices: ");
		printPrice(popeyesPrice);
		printPrice(popeyesPrevPrice);
		printPrice(googlePrice);
		printPrice(googlePrevPrice);
		printPrice(cokePrice);
		printPrice(cokePrevPrice);
		printPrice(coronaPrice);
		printPrice(coronaPrevPrice);
		printPrice(facebookPrice);
		printPrice(facebookPrevPrice);
		printPrice(applePrice);
		printPrice(applePrevPrice);
		printPrice(nikePrice);
		printPrice(nikePrevPrice);
		printPrice(threemPrice);
		printPrice(threemPrevPrice);
		printPrice(hersheyPrice);
		printPrice(hersheyPrevPrice);
		printPrice(wholeFoodsPrice);
		printPrice(wholeFoodsPrevPrice);
		*/
		
		setExcelValues(stocks, sheet, cell, row, stocks.get("PLKI").getSymbol(), 0, popeyesPrice, popeyesPrevPrice);
		setExcelValues(stocks, sheet, cell, row, stocks.get("GOOGL").getSymbol(), 1, googlePrice, googlePrevPrice);
		setExcelValues(stocks, sheet, cell, row, stocks.get("COKE").getSymbol(), 2, cokePrice, cokePrevPrice);
		setExcelValues(stocks, sheet, cell, row, stocks.get("STZ").getSymbol(), 3, coronaPrice, coronaPrevPrice);
		setExcelValues(stocks, sheet, cell, row, stocks.get("FB").getSymbol(), 4, facebookPrice, facebookPrevPrice);
		setExcelValues(stocks, sheet, cell, row, stocks.get("AAPL").getSymbol(), 5, applePrice, applePrevPrice);
		setExcelValues(stocks, sheet, cell, row, stocks.get("NKE").getSymbol(), 6, nikePrice, nikePrevPrice);
		setExcelValues(stocks, sheet, cell, row, stocks.get("MMM").getSymbol(), 7, threemPrice, threemPrevPrice);
		setExcelValues(stocks, sheet, cell, row, stocks.get("HSY").getSymbol(), 8, hersheyPrice, hersheyPrevPrice);
		setExcelValues(stocks, sheet, cell, row, stocks.get("WFM").getSymbol(), 9, wholeFoodsPrice, wholeFoodsPrevPrice);
		  
	    fileOut = new FileOutputStream("/Users/leonardobenitez/Desktop/eclipse-stock-workspace/Stock/src/application/stocksheet.xlsx");
	    wb.write(fileOut);
	    fileOut.close();
		
	}
	public void getStockPrice(String symbol) throws IOException{
		//read stuff from the excel sheet
	    //System.out.println("Enter a stock symbol that you would like to receive info for: ");
	    //Scanner in = new Scanner(System.in);
	    //symbol = in.next();
	    
	    FileInputStream excel = new FileInputStream("/Users/leonardobenitez/Desktop/eclipse-stock-workspace/Stock/src/application/stocksheet.xlsx");
	    Workbook temp = new XSSFWorkbook(excel);
	    Sheet sheet = temp.getSheetAt(0);
	    Row row;
	    Cell cell;
	    double price = 0, prevPrice = 0;
	    int count = 0;
	    for(int i = 0; i < 10; i++){
	    	row = sheet.getRow(i);
	    	cell = row.getCell(0);
	    	if(cell.getStringCellValue().equals(symbol)){
	    		cell = row.getCell(1);
	    		price = cell.getNumericCellValue();
	    		cell = row.getCell(2);
	    		prevPrice = cell.getNumericCellValue();
	    		count = 1;
	    	}
	    
	    }
	    
	    if(count == 0){
    	//System.out.println(symbol + " does not exist in the sheet.");
    	}else if(count == 1){
	    //System.out.println(symbol + " price is: " + price + " prev price is: " + prevPrice);
	    setPrice(price);
	    setPrevPrice(prevPrice);
    	}
	    
	    temp.close();
	    excel.close();
	}
	
	public double getPrice(){
		return price;
	}
	public double getPrevPrice(){
		return prevPrice;
	}
	public void setPrice(double p){
		this.price = p;
	}
	public void setPrevPrice(double p){
		this.prevPrice = p;
	}
	
	/*
	 * private static void printPrice(BigDecimal bd){
		System.out.println("Price: " + bd);
	}
	*/
	
	private static BigDecimal getPrice(String s, Map<String, Stock> q) throws IOException{
		return q.get(s).getQuote(true).getPrice();
	}
	
	private static BigDecimal getPrevPrice(String s, Map<String, Stock> q) throws IOException{
		return q.get(s).getQuote(true).getPreviousClose();
	}
	
	private static void setExcelValues(Map<String, Stock> m, Sheet sh, Cell c, Row r, String s, int rNum, BigDecimal price, BigDecimal prevPrice){
		r = sh.getRow(rNum);
		c = r.getCell(0);
	    if (c == null)
	        c = r.createCell(0);
	    c.setCellType(Cell.CELL_TYPE_STRING);
	    c.setCellValue(m.get(s).getSymbol());
	    c = r.getCell(1);
	    if (c == null)
	        c = r.createCell(1);
	    c.setCellType(Cell.CELL_TYPE_NUMERIC);
	    c.setCellValue(price.doubleValue());
	    c = r.getCell(2);
	    if (c == null)
	        c = r.createCell(2);
	    c.setCellType(Cell.CELL_TYPE_NUMERIC);
	    c.setCellValue(prevPrice.doubleValue());
	}
}
