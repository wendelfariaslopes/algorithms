package programs.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

public class Decode64 {

	public static void main(String[] args) {
		String originalInput = "test input";
		String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
		
		System.out.println(encodedString);
		
		decode_decompress("{\"method\":\"utf8-gzip-base64\",\"data\":\"H4sIAAAAAAAAAF2PwQ6CMBBE73xFs3cEDiaYUDzIARITjGg8V7pCI7aEboz+vYhFE/c0M4fZN8n6cevYHQerjOYQLUJgqGsjlW44FFXpx/Fy5UewTr0kE7Y9GzFItkchS909OVxEZxFYrqRE/Wc3oqex1s5x6rHxkszUV/vRXx+yLV6IQ/gGOJjeqZOS1DqsHFXTkjOF5LAbVI0b1IQD/OqmygqJxgU2PWpS1KFMgm/0exxMnx1V4LBGMc9MvRePvK5/HwEAAA==\"}");
		

	}
	
	public static void decode_decompress(String string) {
		byte[] decodedBytes = Base64.getDecoder().decode(string);
	    
	    ByteArrayOutputStream stream = new ByteArrayOutputStream();
	    Inflater decompresser = new Inflater(false);
	    InflaterOutputStream inflaterOutputStream = new InflaterOutputStream(stream, decompresser);
	    try {
	        inflaterOutputStream.write(decodedBytes);
	        inflaterOutputStream.close();
	        String data = stream.toString();
	        System.out.println(data);
	    } catch (IOException e) {
	        System.out.println(e);
	    }
	}

}
