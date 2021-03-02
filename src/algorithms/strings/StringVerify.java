package algorithms.strings;

import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.emory.mathcs.backport.java.util.Arrays;

public class StringVerify {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = { "MSFT", "2020", "145.3" };
		String json = "{\"timelineId\":1583985600000,\"symbol\":\"MSFT\",\"date\":{\"year\":2020,\"month\":2,\"dayOfMonth\":12,\"hourOfDay\":0,\"minute\":0,\"second\":0},\"open\":145.300003,\"low\":138.580002,\"high\":153.470001,\"close\":139.059998,\"adjClose\":138.339371,\"volume\":93226400,\"key\":\"00bad6e6-be07-48fb-ba87-e30dd32eda87\"}";

		if (containsIndexOf(json, words)) {
			System.out.println("Contains: " + Arrays.toString(words));
		} else {
			System.out.println("Not found");
		}
//
//		// Get a value specifying Field name
//		String fieldName = "message";
//		JsonObject jobj = new Gson().fromJson(json, JsonObject.class);
//		String result = jobj.get(fieldName).toString();
//		System.out.println(result);

//		String var = "{\"ETI\":\"MANGROUP_LNDCPRO\",\"ETITime\":\"2020-07-15T16:48:57.598+02:00\",\"ETITimeNano\":\"2020-07-15T14:48:57.598Z\",\"MIC\":\"XSTO\",\"TSGroupOrderId\":\"20200715-143212270-558-273\",\"TSId\":\"20200715-144857234-695-6277\",\"TSOrderId\":\"20200715-144718729-444-6266\",\"allocationBestFit\":false,\"allocationIsAveragePricing\":true,\"allocationOrdinal\":0E-8,\"allocationRestrictionType\":\"Both\",\"allocationRoundingType\":\"NotApplicable\",\"allocationState\":\"UnAllocated\",\"allocationTSId\":\"20200715-144721349_200125\",\"allocationTemplateEntries\":[{\"allocPercentage\":73.29476167,\"allocPriority\":1.00000000,\"allocationTSId\":\"20200714-MANGROUP_LNSTPRO-3966-STG\",\"clearingAccount\":\"GLG\",\"clearingBroker\":\"TS-SS\",\"defaultEntry\":false,\"ignoreValidation\":false,\"minAllocQty\":1.00000000,\"minTradeLot\":1.00000000,\"ordinal\":0E-8,\"quantity\":82343.00000000,\"side\":\"Buy\"},{\"allocPercentage\":26.70523833,\"allocPriority\":1.00000000,\"allocationTSId\":\"20200714-MANGROUP_LNSTPRO-3967-STG\",\"clearingAccount\":\"GLG\",\"clearingBroker\":\"TS-SS\",\"defaultEntry\":false,\"ignoreValidation\":false,\"minAllocQty\":1.00000000,\"minTradeLot\":1.00000000,\"ordinal\":0E-8,\"quantity\":30002.00000000,\"side\":\"Buy\"}],\"allocationTemplateName\":\"20200715-144718729-444-6266\",\"allocationTemplateType\":\"PreTrade\",\"alternateOwner\":\"BBOUILLOU_PROD\",\"autoAck\":false,\"avgPrice\":0E-8,\"branchAccountId\":\"GLG\",\"capacityType\":\"Agency\",\"captureDate\":\"2020-07-15T16:44:29.000+02:00\",\"captureDateNano\":\"2020-07-15T14:44:29Z\",\"clearingBroker\":\"JPMC\",\"clearingBrokerShortName\":\"JPMC\",\"clientLEIStr\":\"549300RJTS0EUAOPTJ96\",\"contractType\":\"RFMD\",\"corpAlias\":\"MANGROUP\",\"corpId\":\"1B10686\",\"counterpartyClOrdId\":\"202007140000123\",\"crossingType\":\"Any\",\"description\":\"Svenska Cellulo -B-\",\"durationType\":\"Day\",\"enhanced\":false,\"exchange\":\"STO\",\"exchangeCountry\":\"SE\",\"exchangeTimeZone\":\"Europe\\/Stockholm\",\"execId\":\"20200715-144857234-695-6277\",\"executedQuantity\":0E-8,\"executingAccount\":\"GLG\",\"executingBroker\":\"100194\",\"executingBrokerETI\":\"JPMCEU\",\"executingBrokerEntity\":{\"TSId\":\"1000463\",\"name\":\"J.P. Morgan Securities plc\",\"shortName\":\"JPM\"},\"executingBrokerShortName\":\"JPMC\"}";
//		getAllKeys("",var);

	}

	public static void getAllKeys(String space, String stringJson) {
		// Get all the Keys from JSON
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(stringJson);
		JsonObject obj = element.getAsJsonObject();
		Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
		for (Map.Entry<String, JsonElement> entry : entries) {
			System.out.println(space + "" + entry.getKey() + " : " + entry.getValue());

			String str = entry.getValue() + "";

			if (parser.parse(str) instanceof JsonObject) {
				space += "  ";
				getAllKeys(space, str);
			}
		}
	}

	public static boolean containsWords(String inputString, String[] words) {
		boolean found = true;
		for (String word : words) {
			if (!inputString.contains(word)) {
				found = false;
				break;
			}
		}
		return found;
	}

	public static boolean containsWordsIndexOf(String inputString, String[] words) {
		boolean found = true;
		for (String word : words) {
			if (inputString.indexOf(word) == -1) {
				found = false;
				break;
			}
		}
		return found;
	}

	public static boolean containsIndexOf(String inputString, String[] words) {
		boolean found = true;

		for (String word : words) {

			int index = inputString.indexOf(word);

			if (index == -1) {
				found = false;
				break;
			} else {
				char c = inputString.charAt(index - 1);
				if (c != ':' && c != '"') {
					found = false;
					break;

				}

			}
		}
		return found;
	}

}
