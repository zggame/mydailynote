/**
 * Generate score from a line of query results.  
 * @author gzhu1
 *
 */
public interface LineMethod {
	
	public double process(int[] lineElements);
	/** 
	 * 
	 * @returnn a full ready-for-print method name (include the score name)
	 */
	public String method();
}
