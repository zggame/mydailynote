package org.imirsel.mirex.eval;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SimpleKey2 implements Keys {
	private int queryNumber,answerNumber;
	private Map<String,Integer> queryIdMap;
	
	
	private static Log log = LogFactory.getLog(SimpleKey2.class);
	@Override
	public String getAnswer(String query) {
		return String.valueOf(lastThreeLetterNumber(query));
	}

	@Override
	public int getAnswerIdByAnswer(String answer) {
		return lastThreeLetterNumber(answer);
	}

	@Override
	public int getAnswerIdByQuery(String query) {
		return lastThreeLetterNumber(query);
	}

	@Override
	public int getAnswerNumber() {
		return answerNumber;
	}

	@Override
	public int getQueryId(String query) {
		return queryIdMap.get(Util.baseName(query));
	}

	@Override
	public int getQueryNumber() {
		return queryNumber;
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
				Scanner sfile=new Scanner(line);
				String name=sfile.next();
				queryIdMap.put(Util.baseName(name), queryNumber);queryNumber++;
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
				log.error(e,e);
			}
		}
		
	}

		
	private int lastThreeLetterNumber(String name){
		String s=Util.baseName(name);
		if (s.length()>=3) return Integer.parseInt(s.substring(s.length()-3))-1;
		else return -1;
	}

}
