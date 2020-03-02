package lexical;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Logger;

public class FileText {
	
	static Logger logger = Logger.getLogger(FileText.class.getName());
	
	private static final String ACCESS_MODE_ONLY_READ = "r";
	private static final String ACCESS_MODE_READ_AND_WRITE = "rw";
	private static final String ACCESS_MODE_READ_AND_WRITE_UPDATE = "rwd";
	
	public static final String FILE_PATH = "/Users/wendellopes/Documents/workspaceNeon/lexical/src/files/dictionaries/easy-words.txt";
	
	public static String read(long pos) throws IOException{
		
		int length = 4;
		StringBuilder sb = new StringBuilder(length);
		
		RandomAccessFile raf = new RandomAccessFile(FILE_PATH , ACCESS_MODE_ONLY_READ);
		long lengthFile = raf.length();
		
		raf.seek(pos);
		System.out.println("Position: "+pos );
		  System.out.println("" + Character.toString ((char) raf.read()));
		  
		  char c = (char) (raf.read() - 1);
		
		String line = null;
		while((line=raf.readLine())!=null){
			if(line.length()==length && c=='\n'){
				sb.append(line);
				break;
			}
		}
		
		return line;
	}
	
	public static void main (String [] args){
		
		long pos = 89;
		boolean noFound = true;
		try {
			
			while(noFound){
				if(read(pos)!=null){
					System.out.println(read(pos));
					noFound = false;
					break;
				}else{
					pos--;
				}
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
