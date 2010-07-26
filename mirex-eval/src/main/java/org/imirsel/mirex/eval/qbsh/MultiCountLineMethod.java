package org.imirsel.mirex.eval.qbsh;

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
