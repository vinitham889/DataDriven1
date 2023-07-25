package com.app.util;

import java.util.ArrayList;

public class TestUtil {

	static XlsReader reader;

	public static ArrayList<Object[]> getDataFromExcel() throws Exception{

		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		try {
			reader = new XlsReader("C:\\Users\\vinitham\\eclipse-workspace\\DataDriven\\src\\main\\java\\com\\app\\sheet\\Register_form (2).xlsx");
		}catch(Exception e) {
			e.printStackTrace();
		}

		int rowCount = reader.getRowCount("Sheet1");

		for(int i=1;i<=rowCount/2-1;i++) {
			String Fn = reader.getCellData("Sheet1","FirstName", i);
			String Ln = reader.getCellData("Sheet1","LastName", i);
			String Add = reader.getCellData("Sheet1","Address", i);
			String Email = reader.getCellData("Sheet1","Email", i);
			String Ph = reader.getCellData("Sheet1","Phone", i);
			String gender = reader.getCellData("Sheet1","Gender", i);
			String hobby = reader.getCellData("Sheet1","Hobby1", i);
			String hobby1 = reader.getCellData("Sheet1","Hobby2", i);
			String hobby2 = reader.getCellData("Sheet1","Hobby3", i);
			String Lang = reader.getCellData("Sheet1","Language", i);
			String Skills = reader.getCellData("Sheet1","Skills", i);
			String Country = reader.getCellData("Sheet1","Country", i);
			String BirthYear = reader.getCellData("Sheet1","BirthYear", i);
			String BirthMonth = reader.getCellData("Sheet1","BirthMonth", i);
			String BirthDay = reader.getCellData("Sheet1","BirthDay", i);
			String Pswd = reader.getCellData("Sheet1","Password", i);
			String ConfPswd = reader.getCellData("Sheet1","ConfirmPswd", i);
			
			Object ob[] = {Fn, Ln, Add, Email, Ph, gender, hobby, hobby1, hobby2, Lang, Skills,
        Country, BirthYear, BirthMonth, BirthDay, Pswd, ConfPswd};
			myData.add(ob);
			}

			return myData;

		}

	}
