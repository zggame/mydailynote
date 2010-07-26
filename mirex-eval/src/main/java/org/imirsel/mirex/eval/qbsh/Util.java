package org.imirsel.mirex.eval.qbsh;

import java.io.IOException;
import java.io.PrintWriter;




public class Util {
	public static String baseName(String fullname){
		if (fullname!=null){
		String[] s=fullname.trim().split("\\\\|/");
		String name=s[s.length-1];
		return name.replaceAll("\\..+|^0*", "").trim();
		}else{
			return "";
		}
	}

	public static void printScoreList(double[] scores, String name) throws IOException {
		PrintWriter file=new PrintWriter(name);
		try{
			int length=scores.length;
			for (int i=0;i<length;i++){
				file.println(scores[i]);
			}
		}finally{
			if (file!=null) file.close();
		}
		
	}

	
}
