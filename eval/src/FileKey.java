import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




public class FileKey implements Keys {

	protected Map<String,String> keyMap;
	private int queryNumber,answerNumber;
	private Map<String,Integer> queryIdMap;
	protected Map<String,Integer> answerIdMap;
	
	private static Log log = LogFactory.getLog(FileKey.class);

	
	@Override
	public String getAnswer(String query) {
		return keyMap.get(Util.baseName(query));
	}

	@Override
	public void init(String query) {
		keyMap=new HashMap<String,String>();
		answerIdMap=new HashMap<String, Integer>();
		queryIdMap=new HashMap<String, Integer>();
		queryNumber=answerNumber=0;
		BufferedReader answerFile=null,
				file=null;
		Scanner s=new Scanner(query);
		s.useDelimiter("\\s*\\|\\|\\s*");
		try{
			String str;
			String line;
			String queryFileName = null,groupFileName=null;
			for (int i=0;i<5;i++) queryFileName=s.next();
			file=new BufferedReader(new FileReader(queryFileName));

			
			while ((line=file.readLine())!=null){
				Scanner sfile=new Scanner(line);
				String name=sfile.next();
				queryIdMap.put(Util.baseName(name), queryNumber);queryNumber++;
			}
			if (file!=null) file.close();
			groupFileName=s.next();
			file=new BufferedReader(new FileReader(groupFileName));
			
			while ((line=file.readLine())!=null){
				Scanner sfile=new Scanner(line);
				String name=sfile.next();
				answerIdMap.put(Util.baseName(name), answerNumber);answerNumber++;
				log.debug("pushed int ("+name+","+(answerNumber-1)+")");
			}
			
			
			do{
				str=s.next();
			}while (s.hasNext());
			
			answerFile=new BufferedReader(new FileReader(str));
			
			while ((line=answerFile.readLine())!=null){
				s=new Scanner(line.trim());
				s.useDelimiter("\\s*,\\s*|\\s+");
				String q=Util.baseName(s.next()).trim();
				String truth=Util.baseName(s.next()).trim();
				//String s1=Util.baseName(q);
				keyMap.put(q,truth);
				
				if (!answerIdMap.containsKey(truth)){
					log.warn("wrong answer with line:"+line);
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if (answerFile!=null) answerFile.close();
				if (file!=null) file.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public int getAnswerNumber() {
		
		return answerNumber;
	}

	@Override
	public int getQueryNumber() {
		
		return queryNumber;
	}

	@Override
	public int getAnswerIdByQuery(String query) {
		
		return answerIdMap.get(Util.baseName(getAnswer(query)));
	}

	@Override
	public int getQueryId(String query) {

		return queryIdMap.get(Util.baseName(query));
	}

	@Override
	public int getAnswerIdByAnswer(String answer) {
		if (answerIdMap.containsKey(Util.baseName(answer))) return answerIdMap.get(Util.baseName(answer));
		else return -1;
	
	}

}
