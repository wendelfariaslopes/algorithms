package programs.google.tests;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesResponse;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.SheetProperties;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import programs.google.ServiceUtil;
import programs.google.SheetsServiceUtil;

public class GoogleSheetTest {

	public static final String SPREADSHEET_ID = "1m_Ty1a_a6_YChP6ovmEoaA6qxfCUGdzc3RqdPG3YyKU";

	public static void main(String[] args) throws IOException, GeneralSecurityException {

		// doSomething();

		//insertData();
		
		createSheet();
	}

	public static boolean createSpreadSheet() throws IOException, GeneralSecurityException {

		boolean status = false;

		Sheets sheetsService = SheetsServiceUtil.getSheetsService();

		Spreadsheet spreadSheet = new Spreadsheet().setProperties(new SpreadsheetProperties().setTitle("WENDEL-SHEET"));

		Spreadsheet result = sheetsService.spreadsheets().create(spreadSheet).execute();

		if (result.getSpreadsheetId() != null) {
			System.out.println(result.getSpreadsheetId());
			status = true;
		}
		return status;
	}

	public static void doSomething() throws IOException, GeneralSecurityException {

		final String spreadsheetId = "1m_Ty1a_a6_YChP6ovmEoaA6qxfCUGdzc3RqdPG3YyKU";
		final String range = "pag1!A:K";
		Sheets service = SheetsServiceUtil.getSheetsService();

		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();

		List<List<Object>> values = response.getValues();

		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
		} else {
			// System.out.println("Name, Major");
//            for (List row : values) {
//                System.out.printf("%s, %s, %s\n", row.get(0), row.get(0), row.get(10));
//            }

			for (List row : values) {
				for (Object cel : row) {
					System.out.print(cel + " ");
				}
				System.out.println();
			}
			
			int numberLines = values.size();
			int numberColumns = values.get(0).size();
			
			for(int i=0; i < numberLines; i++) {
				for(int j=0; j < numberColumns; j++) {
					System.out.print(values.get(i).get(j) + " ");
				}
				System.out.println();
			}
		}
	}

	public static boolean insertData() throws IOException, GeneralSecurityException {

		ValueRange vr = new ValueRange();

		Sheets sheetsService = SheetsServiceUtil.getSheetsService();

		final String RANGE = "pag2!A:K";

		ValueRange body = new ValueRange().setValues(Arrays.asList(Arrays.asList("Expenses January"),
				Arrays.asList("books", "30"), Arrays.asList("pens", "10"), Arrays.asList("Expenses February"),
				Arrays.asList("clothes", "20"), Arrays.asList("shoes", "5")));

		UpdateValuesResponse result = sheetsService.spreadsheets().values().update(SPREADSHEET_ID, RANGE, body)
				.setValueInputOption("RAW").execute();

		List<ValueRange> data = new ArrayList<>();
		data.add(new ValueRange().setRange("pag2!D1")
				.setValues(Arrays.asList(Arrays.asList("January Total", "=B2+B3"))));
		data.add(new ValueRange().setRange("pag2!D4")
				.setValues(Arrays.asList(Arrays.asList("February Total", "=B5+B6"))));

		BatchUpdateValuesRequest batchBody = new BatchUpdateValuesRequest().setValueInputOption("USER_ENTERED")
				.setData(data);
		BatchUpdateValuesResponse batchResult = sheetsService.spreadsheets().values()
				.batchUpdate(SPREADSHEET_ID, batchBody).execute();

		List<String> ranges = Arrays.asList("pag2!E1", "pag2!E4");
		BatchGetValuesResponse readResult = sheetsService.spreadsheets().values().batchGet(SPREADSHEET_ID)
				.setRanges(ranges).execute();

		ValueRange januaryTotal = readResult.getValueRanges().get(0);
		// assertThat(januaryTotal.getValues().get(0).get(0)).isEqualTo("40");

		ValueRange febTotal = readResult.getValueRanges().get(1);
		// assertThat(febTotal.getValues().get(0).get(0)).isEqualTo("25");

		ValueRange appendBody = new ValueRange().setValues(Arrays.asList(Arrays.asList("Total", "=E1+E4")));

		AppendValuesResponse appendResult = sheetsService.spreadsheets().values()
				.append(SPREADSHEET_ID, RANGE, appendBody).setValueInputOption("USER_ENTERED")
				.setInsertDataOption("INSERT_ROWS").setIncludeValuesInResponse(true).execute();

		ValueRange total = appendResult.getUpdates().getUpdatedData();

		return true;

	}

	public static void createSheet() throws IOException, GeneralSecurityException {


		Sheets sheetsService = ServiceUtil.getSheetsService();
		
		Spreadsheet spreadSheet = new Spreadsheet().setProperties(new SpreadsheetProperties().setTitle("SUUUUUPER-sheet"));
		
		//Create Sheets
		List<Sheet> sheets = new ArrayList<>();
		for(int i=0; i < 5; i++) {
			sheets.add(new Sheet().setProperties(new SheetProperties().setTitle("sheet"+i)));
		}
		spreadSheet.setSheets(sheets);
		
		Spreadsheet result = sheetsService.spreadsheets().create(spreadSheet).execute();
		

		if (result.getSpreadsheetId() != null) {
			System.out.println("Created SpreadSheet Google ID = "+result.getSpreadsheetId());
			System.out.println("URL = "+result.getSpreadsheetUrl());
			System.out.println("Name = "+result.getProperties().getTitle());
		}

	}

	
}
