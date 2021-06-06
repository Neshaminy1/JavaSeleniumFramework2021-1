package gov.acms.acmsproject.sample;

import org.testng.annotations.Test;

import gov.acms.acmsproject.utils.Xls_Connection;

public class ExcelValidation {
	
	
	@Test
	public void Excel(){
		Xls_Connection reader = new Xls_Connection("./src/test/resources/testdata/data.xlsx");
		String sheetName = "LoginDataSheet";
		
		int rowCount = reader.getRowCount(sheetName);

		for(int rowNum=2; rowNum<=rowCount; rowNum++){
			
			if (reader.getCellData(sheetName, "Execute", rowNum).contentEquals("Y")){
				
				String loginId = reader.getCellData(sheetName, "UserName", rowNum);
				String passsword = reader.getCellData(sheetName, "PassWord", rowNum);
	
				System.out.println(loginId + " " + passsword);
				
				//Enter Status in Excel
				reader.setCellData(sheetName, "Status", rowNum, "PASS");
			
				
			}

		}
		
	}

}
