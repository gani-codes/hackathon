package baseClasses;

import java.io.FileInputStream;

public class ExcelUtils extends BaseClass{
	public static String[] readExcelData(String sheetName) throws Exception {
		String testData[] = new String[5];
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"testData.xlsx");

		
		return testData;
	}
}
