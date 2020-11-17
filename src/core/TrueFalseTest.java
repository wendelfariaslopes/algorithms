package core;

public class TrueFalseTest {

	

	public static void main(String[] args) {
		
		boolean isQuantityChangeForbidden = false;
		boolean isLimitPriceAmendForbidden = false;
		boolean isDurationTypeAmendForbidden = false;
		
		if (isAmendInstructionRequiresNonAliveOrder(isQuantityChangeForbidden, isLimitPriceAmendForbidden, isDurationTypeAmendForbidden)) {
			System.out.println("Invalid");
		} else {
			System.out.println("Valid");
		}

	}

	private static boolean isAmendInstructionRequiresNonAliveOrder(boolean isQuantityChangeForbidden, boolean isLimitPriceAmendForbidden, boolean isDurationTypeAmendForbidden) {
		return isQuantityChangeForbidden || isLimitPriceAmendForbidden || isDurationTypeAmendForbidden;
	}

}
