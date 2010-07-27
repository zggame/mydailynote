import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class SimpleEvalFile implements EvalFile {

	@Override
	public Result evalResult(String query) {
		Scanner resultScan=null;
		try{
		resultScan=new Scanner(new BufferedReader(new FileReader(query)));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}finally{
			if (resultScan!=null) resultScan.close();
		}
		return null;
	}

	

}
