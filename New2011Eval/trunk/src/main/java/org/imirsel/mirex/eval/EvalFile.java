package org.imirsel.mirex.eval;

/**
 * Evaluate a single result file with parameters from query String.  
 * @author gzhu1
 *
 */


public interface EvalFile {
	
	public Result evalResult(String query);
   
}
