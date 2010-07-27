
public class MyEvalFile2 extends MyEvalFile {
	
	@Override
	public String preprocess(String line){
		return line.replaceAll("^.:","");
	}
}
