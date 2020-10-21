package programs.google.sheet;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.gson.Gson;
import com.organon.oms.pricing.AverageGainLoss;
import com.organon.oms.pricing.Indicator;
import com.organon.oms.pricing.IndicatorTechnical;
import com.organon.oms.pricing.PriceBean;
import com.organon.oms.pricing.RSI;

import programs.google.SheetsServiceUtil;

public class Sheet {

	public static void main(String[] args) {

		Sheets sheetsService = null;

		// final String spreadsheetId = "1O8fXRlz-Y2gBJSs6siijHCtwuU9uUD6UBlixaeWqCYA";

		try {
			sheetsService = SheetsServiceUtil.getSheetsService();

			// calc(sheetsService, spreadsheetId);

			final String spreadsheetId = "1Ncp2wy9TBpTywBTNBOAtPsxT9rzoVTi58b-S-0EQ4vA";
			final String range = "RSI!A3:L";
			int[] columns = { 1, 2, 3, 10 };

			readerValues(spreadsheetId, range);

		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (GeneralSecurityException e1) {
			e1.printStackTrace();
		}

	}

	public static void calc(Sheets sheetsService, String spreadsheetId) {
		List<ValueRange> data = new ArrayList<>();
		data.add(new ValueRange().setRange("A1").setValues(Arrays
				.asList(Arrays.asList("Ticker", "NASDAQ:GOOG", "=GOOGLEFINANCE(B1;\"price\";B2-B3;B2;\"DAILY\")"))));
		data.add(new ValueRange().setRange("A2").setValues(Arrays.asList(Arrays.asList("Today", "=TODAY()"))));
		data.add(new ValueRange().setRange("A3").setValues(Arrays.asList(Arrays.asList("Days Before", "30"))));

		BatchUpdateValuesRequest batchBody = new BatchUpdateValuesRequest().setValueInputOption("USER_ENTERED")
				.setData(data);

		try {

			BatchUpdateValuesResponse batchResult = sheetsService.spreadsheets().values()
					.batchUpdate(spreadsheetId, batchBody).execute();
			List<String> ranges = Arrays.asList("C2", "D2");
			BatchGetValuesResponse readResult = sheetsService.spreadsheets().values().batchGet(spreadsheetId)
					.setRanges(ranges).execute();

			// ValueRange januaryTotal = readResult.getValueRanges().get(0);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readerValues() throws IOException, GeneralSecurityException {

		final String spreadsheetId = "1Ncp2wy9TBpTywBTNBOAtPsxT9rzoVTi58b-S-0EQ4vA";
		final String range = "RSI!A:L";
		Sheets service = SheetsServiceUtil.getSheetsService();

		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();

		List<List<Object>> values = response.getValues();

		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
		} else {

//			int numberLines = values.size();
//			int numberColumns = values.get(0).size();
//			
//			for(int i=0; i < numberLines; i++) {
//				for(int j=0; j < numberColumns; j++) {
//					System.out.print(values.get(i).get(j) + " ");
//				}
//				System.out.println();
//			}

			for (List row : values) {
				for (Object cel : row) {
					System.out.print(cel + " ");
				}
				System.out.println();
			}
		}
	}

	public static void readerValues(String spreadsheetId, String range, int[] columns)
			throws IOException, GeneralSecurityException {

		List<PriceBean> listPriceBean = new ArrayList<>();

		Sheets service = SheetsServiceUtil.getSheetsService();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();

		List<List<Object>> values = response.getValues();

		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
		} else {

			for (List row : values) {
				for (int column : columns) {
					if (row.size() > column) {
						System.out.print(row.get(column) + " ");
					}
				}
				System.out.println();
			}
		}
	}

	public static void readerValues(String spreadsheetId, String range) throws IOException, GeneralSecurityException {

		int[] columns = { 1, 2, 3 };

		

		Sheets service = SheetsServiceUtil.getSheetsService();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();

		List<List<Object>> values = response.getValues();
		
		List<String> listStr = new ArrayList<>();
		

		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
		} else {

			for (List row : values) {
				String s = "";
				for (int column : columns) {
					if (row.size() > column) {
						s+=row.get(column) + " ";
					}
				}
				listStr.add(s.trim());
			}
		}
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<PriceBean> listPriceBean = new ArrayList<>();
		
		
		for (String string : listStr) {
			
			//System.out.print(string);
			String[] row = string.split(" ");
	
			
			if(row.length == 3 ) {
			
				//int i = Integer.parseInt(row[0]);
				
				//if(i <= (period + 1)) {
					Date date;
					try {
						date = sdf.parse(row[1]);
						double price = parse(row[2], Locale.US).doubleValue();

						listPriceBean.add(new PriceBean(date.getTime(), date, 0.0, 0.0, 0.0, 0.0, price/10000, 1));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				//}
			}
			
		}
		
		Gson g = new Gson();
		
		
		//listPriceBean.forEach(l-> System.out.println(g.toJson(l)));
		
		System.out.println("");
		System.out.println("Filter ");
		
		//14/12/2009
		//28/12/2009
		
		//List<PriceBean> list = Indicator.filter(listPriceBean, RSI.between("14/12/2009", "28/12/2009"));
		//List<PriceBean> list = Indicator.firstList(listPriceBean, 14);
		
		//list.forEach(a-> System.out.println(g.toJson(a)));
		//double d = IndicatorTechnical.getRSI2(listPriceBean);
		

		RSI rsi14 = new RSI(listPriceBean, 14);
		
		RSI rsi5 = new RSI(listPriceBean, 5);
		

		for (RSI i14 : rsi14.getListRSI()) {
			
			for (RSI i5 : rsi5.getListRSI()) {
				if(i14.getTimeline()==i5.getTimeline()) {
					System.out.println(i14.getDate()+" --> RSI 14 = "+i14.getRSI()+ " RSI 5 = "+i5.getRSI());
				}
			}
		}
		
	}

	public static BigDecimal parse(final String amount, final Locale locale) throws ParseException {
		final NumberFormat format = NumberFormat.getNumberInstance(locale);
		if (format instanceof DecimalFormat) {
			((DecimalFormat) format).setParseBigDecimal(true);
		}
		return (BigDecimal) format.parse(amount.replaceAll("[^\\d.,]", ""));
	}
}
