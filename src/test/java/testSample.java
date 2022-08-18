import java.io.IOException;
import java.util.ArrayList;

public class testSample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		dataDriven d = new dataDriven();
		ArrayList<String> data = d.getData("Add profile");
		int size = data.size();
		for(int i = 0; i < size; i++) {
			System.out.println(data.get(i));
		}
		

	
	}

}
