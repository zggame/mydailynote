package org.imirsel.mirex.eval;
import org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor;

/**
 * Key system for query and answer.  
 * 
 * All queries and answers are mapped to ID(int). 
 * This facilitates the counting and statistics.  
 * {@link init(query)}  needs to run first and prepare the mapping for later search.  
 * @author gzhu1
 *
 */
public interface Keys {
	public String getAnswer(String query);
	public void init(String query);
	public int getQueryNumber();
	public int getAnswerNumber();
	public int getQueryId(String query);
	public int getAnswerIdByQuery(String query);
	public int getAnswerIdByAnswer(String answer);
	
}
