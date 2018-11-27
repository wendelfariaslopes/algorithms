package algorithms.comparator;

import java.util.Comparator;

public class Bean {

	private String name;
	private double riskValue;
	private double returnValue;

	private double maxRisk;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRiskValue() {
		return riskValue;
	}

	public void setRiskValue(double riskValue) {

		this.riskValue = riskValue;

	}

	public double getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(double returnValue) {
		this.returnValue = returnValue;
	}

	public double getMaxRisk() {
		return maxRisk;
	}

	public void setMaxRisk(double maxRisk) {
		this.maxRisk = maxRisk;
	}

	public static Comparator<Bean> COMPARE_BY_NAME = new Comparator<Bean>() {
		public int compare(Bean one, Bean other) {
			return one.name.compareTo(other.name);
		}
	};

	public static Comparator<Bean> COMPARE_BY_RETURN = new Comparator<Bean>() {
		public int compare(Bean one, Bean other) {
			return one.returnValue < other.returnValue ? 1 : one.returnValue > other.returnValue ? -1 : 0;
		}
	};

	public static Comparator<Bean> COMPARE_BY_RISK = new Comparator<Bean>() {
		public int compare(Bean one, Bean other) {
			return one.riskValue > other.riskValue ? 1 : one.riskValue < other.riskValue ? -1 : 0;
		}
	};

}
