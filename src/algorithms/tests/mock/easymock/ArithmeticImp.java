package algorithms.tests.mock.easymock;

public class ArithmeticImp implements Arithmetics {
	
	
	private int value1;
	private int value2;

	public int getValue1() {
		return value1;
	}

	public void setValue1(int value1) {
		this.value1 = value1;
	}

	public int getValue2() {
		return value2;
	}

	public void setValue2(int value2) {
		this.value2 = value2;
	}

	@Override
	public int sum() {
		return value1+value2;
	}

	@Override
	public int subtract() {
		return value1-value2;
	}
	
}
