package data;
import java.io.IOException;
import java.util.ArrayList;

public class TestSample {
	public static void main(String[] args) throws IOException {
		DataDriven d = new DataDriven();
		ArrayList data=d.getData("RestAddBook","RestAssured");
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));
		System.out.println(data.get(4));
		//System.out.println(data.get(5));


	}

}
