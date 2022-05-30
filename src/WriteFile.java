import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class WriteFile {
	private String fileName;
	private String text;
	
	public WriteFile(String fileName, String text) {
		this.fileName = fileName;
		this.text = text;
	}
	
	public void write() {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(fileName, true));
			bw.write(text + "\n");  //just writes text to the eternal file
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(bw != null) {
					//this closes the file after it finishes doing its process
					bw.close();
					
				}
			} catch(IOException e) {
				
				e.printStackTrace();
			}
		}
	}
}
