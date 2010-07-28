import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




public class SimpleKey implements Keys {
	private int queryNumber,answerNumber;
	private Map<String,Integer> queryIdMap;
	
	
	private static Log log = LogFactory.getLog(SimpleKey.class);


	@Override
	public String getAnswer(String query) {
		if (query!=null) {
			
			return Util.baseName(query);
		}
		return "";
	}

	@Override
	public void init(String query) {
		
		
		queryIdMap=new HashMap<String, Integer>();
		queryNumber=answerNumber=0;
		BufferedReader 	file=null;
		Scanner s=new Scanner(query);
		s.useDelimiter("\\s*\\|\\|\\s*");
		try{
			
			String line;
			String queryFileName = null;
			for (int i=0;i<5;i++) queryFileName=s.next();
			file=new BufferedReader(new FileReader(queryFileName));

			
			while ((line=file.readLine())!=null){
				//Scanner sfile=new Scanner(line);
				//String name=sfile.next();
				queryIdMap.put(twoLevelName(line), queryNumber);queryNumber++;
			}
			
			 answerNumber=Integer.parseInt(s.next());
			
			
		}catch(NumberFormatException e){
			log.error(e,e);
		}
			catch(IOException e){
			log.error(e,e);
		}finally{
			try{
				
				if (file!=null) file.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public int getAnswerIdByQuery(String query) {
		try{
			return Integer.parseInt(Util.baseName(query))-1;
		}catch(NumberFormatException e){
			log.error(e,e); return -1;
		}
		
	}

	@Override
	public int getAnswerNumber() {
		return answerNumber;
	}

	@Override
	public int getQueryId(String query) {
		return queryIdMap.get(twoLevelName(query));
	}

	@Override
	public int getQueryNumber() {
		return queryNumber;
	}

	@Override
	public int getAnswerIdByAnswer(String answer) {
		try{
			int id=Integer.parseInt(Util.baseName(answer))-1;
			return id;
		}catch(NumberFormatException e){
			 return -1;
		}
	}
	
	static final private Pattern TWOLEVEL=Pattern.compile("year2[^\\s]*\\.wav");
	
	
	private String twoLevelName(String fullname){
		Matcher match=TWOLEVEL.matcher(fullname);
		if (match.find()) return fullname.substring(match.start(),match.end());
		else {
			log.warn(fullname);return null;
		}
	}

}
