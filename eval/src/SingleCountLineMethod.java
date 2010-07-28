/**
 * Line method that return the score by the first hit.  
 * 
 * @author gzhu1
 *
 */

public class SingleCountLineMethod implements LineMethod {
	private Score scoreMethod;

	public void setScoreMethod(Score scoreMethod) {
		this.scoreMethod = scoreMethod;
	}

	@Override
	public double process(int[] lineElements) {
	
		for (int i = 1; i < lineElements.length; i++) {
			if (lineElements[0]==lineElements[i])
				return scoreMethod.score(i);
		}
		return 0;
	}

	@Override
	public String method() {
		// TODO Auto-generated method stub
		return "Single Count (" + scoreMethod.method() + ")";
	}

}
