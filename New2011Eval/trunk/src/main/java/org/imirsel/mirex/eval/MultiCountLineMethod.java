package org.imirsel.mirex.eval;
/**
 * Line method that return the score by sum all hits.  
 * For example, if there are 5 hits in the 10 return results,
 * total would be the sum of the scores of all 5 hits. 
 * @author gzhu1
 *
 */
public class MultiCountLineMethod implements LineMethod {

	private Score scoreMethod;
	@Override
	public double process(int[] lineElements) {
		
		double score=0;
		for (int i = 1; i < lineElements.length; i++) {
			if (lineElements[0]==lineElements[i])
				score+=scoreMethod.score(i);
		}
		return score;
	}
	public void setScoreMethod(Score scoreMethod) {
		this.scoreMethod = scoreMethod;
	}
	@Override
	public String method() {
		
		return "Multi Count ("+scoreMethod.method()+")";
	}


}
