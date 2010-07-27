

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyEvalFile implements EvalFile {
	private List<LineMethod> lineMethods;
	private Keys keys;
	private static Log log = LogFactory.getLog(MyEvalFile.class);

	public void setKeys(Keys keys) {
		this.keys = keys;
	}@Override
	public Result evalResult(String query) {
		Scanner s = null;
		BufferedReader file = null;
		try {
			
			keys.init(query);
			Scanner scan=new Scanner(query.trim());
			scan.useDelimiter("\\s*\\|\\|\\s*|\\s*\\n|\\s*\\r");
			String fileName=scan.next();
			file = new BufferedReader(new FileReader(fileName));
			String title=scan.next()+"_"+scan.next()+"_"+scan.next();
			scan.next();scan.next();
			String note=scan.next();
			String line;
			String[] elements=new String[11];
			int[] lineElement=new int[11];
			Result result=new Result();
			result.setTitle(title);
			result.addNote(note);
			int count=0;
			double[] totalScores=new double[lineMethods.size()];
			List<double[]> queryScoresList=new ArrayList<double[]>();
			List<double[]> groupScoresList=new ArrayList<double[]>();
			int answerNumber=keys.getAnswerNumber();
			int[] groupCount=new int[answerNumber];
			
			
			for (int i=0;i<answerNumber;i++)groupCount[i]=0;
			for (int i=0; i<lineMethods.size();i++){
				totalScores[i]=0.0; 
				queryScoresList.add(new double[keys.getQueryNumber()]);
				double[] groupScoresTotal=new double[answerNumber];
				for (int j=0; j<answerNumber;j++)groupScoresTotal[j]=0.0;	
				groupScoresList.add(groupScoresTotal);
			}
			while ((line = file.readLine()) != null) {	
				line=preprocess(line);
				
				s = new Scanner(line);
				s.useDelimiter("\\s+|\\s*[:,;]\\s*");
				int i;
				for ( i=0; (i<11)&&(s.hasNext());i++){
					elements[i]=s.next();
				}
				if ((s.hasNext())||(i<11)){
					log.error("wrong line:"+line);
					result.addNote("wrong line:"+line+"\n");
				}else{
					String querys=elements[0];
					//String answer=keys.getAnswer(querys);
					int answerId=lineElement[0]=keys.getAnswerIdByQuery(querys);
					groupCount[answerId]++;
					count++;
					
					for (i=1; i<=10; i++){
						lineElement[i]=keys.getAnswerIdByAnswer(elements[i]);
					}
					for ( i=0; i<lineMethods.size();i++) {
						//double d=totalScores.get(i);
						double score=lineMethods.get(i).process(lineElement);
						queryScoresList.get(i)[keys.getQueryId(querys)]=score;
						groupScoresList.get(i)[answerId]+=score;	
						log.debug("query is :"+querys+"  and  AnswerID is :"+answerId);
						
						totalScores[i]+=score;
					}
					
				}
			}//while
			result.addNote("file:"+fileName+"\n");
			result.setSize(count);
			for (int i=0;i<lineMethods.size();i++){
				
				
				for (int j=0;j<answerNumber;j++) groupScoresList.get(i)[j]/=(double)groupCount[j];
				result.addScore(lineMethods.get(i).method(),totalScores[i]/(double)count,
						queryScoresList.get(i),groupScoresList.get(i));
			}
			log.info("done query:"+query);	
			return result;
		} catch (IOException e) {
			log.error(e,e);
		} finally {
			try{
				if (file != null) {
			
				file.close();
				}
			}catch(IOException e){
				log.error(e,e);
			}

		}
		return null;
	}

	public String preprocess(String line){
		return line.replaceAll("^.:|.*[/\\\\]","");
	}

	public void setLineMethods(List<LineMethod> lineMethods) {
		this.lineMethods = lineMethods;
	}



}
