package org.imirsel.mirex.eval;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class CombineReport {

	private static Log log = LogFactory.getLog(CombineReport.class);

	/**Combine the reports from individual algorithms into one nice formatted csv file. 
         * example CombineReport 2011.report.txt
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader reader = null;
		try {
			String reportGeneratorName;
			if ((args==null)||(args.length==0)||(args[0].isEmpty())) {
				reportGeneratorName="reportGenerate.txt";
			}else {
				reportGeneratorName=args[0];
			}
			reader = new BufferedReader(new FileReader(reportGeneratorName));
		
			String titleLine,outputLine,inputLine;
			Scanner s;
			
			do{
				outputLine=reader.readLine();
			}while ((outputLine!=null) &&(outputLine.startsWith("##")));
			while (outputLine!= null) {
				titleLine=reader.readLine();
				BufferedWriter output=new BufferedWriter(new FileWriter(outputLine));
				s = new Scanner(titleLine);
				s.useDelimiter("\\s*\\|\\|\\s*|\\s*\\n|\\s*\\r");
				int fileCount=0;
				output.write("*"); 
				while (s.hasNext()){
					fileCount++; output.append(", ").append(s.next());
				}
				output.newLine();
				BufferedReader[] inputs=new BufferedReader[fileCount];
				for (int i=0;i<fileCount;i++){
					inputs[i]=new BufferedReader(new FileReader(reader.readLine()));
				}
				int lineCount=0;
				while (inputs[0].ready()){
					output.write(String.valueOf(++lineCount));
					
					for (int i=0;i<fileCount;i++){
						Double score=Double.valueOf(inputs[i].readLine());
						output.append(", ").append(String.format("%.3f", score));
					}
					output.newLine();
				}
				output.close();
				for (int i=0;i<fileCount;i++){
					inputs[i].close();
				}
				log.info("Finish generating report: "+outputLine);
				do{
					outputLine=reader.readLine();
				}while ((outputLine!=null) &&(outputLine.startsWith("##")));
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
