import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWriting {
	
	private String fileName;
	private ArrayList<String> states;
	
	public FileWriting(String fileName, ArrayList<String> states) {
		this.fileName = fileName;
		this.states = states;
	}
	
	public void write() {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(fileName, true)); //appends to the end of the file
			bw.write(states + "\n");  //writes the current state to the external txt file
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
