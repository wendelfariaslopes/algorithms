package algorithms.ai.ml;

import java.nio.ByteBuffer;

public class Converter {
	
	public static int byteArrayToInt(byte[] encodedValue) {
	    int value = (encodedValue[3] << (Byte.SIZE * 3));
	    value |= (encodedValue[2] & 0xFF) << (Byte.SIZE * 2);
	    value |= (encodedValue[1] & 0xFF) << (Byte.SIZE * 1);
	    value |= (encodedValue[0] & 0xFF);
	    return value;
	}
	
	public static byte[] intToByteArray(int value) {
	    byte[] encodedValue = new byte[Integer.SIZE / Byte.SIZE];
	    encodedValue[3] = (byte) (value >> Byte.SIZE * 3);
	    encodedValue[2] = (byte) (value >> Byte.SIZE * 2);   
	    encodedValue[1] = (byte) (value >> Byte.SIZE);   
	    encodedValue[0] = (byte) value;
	    return encodedValue;
	}
	
	public static double byteArrayToDouble(byte[] bytes) {
	    return ByteBuffer.wrap(bytes).getDouble();
	}

}
