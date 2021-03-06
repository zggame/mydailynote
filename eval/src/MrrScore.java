/**
 * MRR score: score=1/pos. 0 for not hit (pos==0).  
 * @author gzhu1
 *
 */
public class MrrScore implements Score {

	@Override
	public double score(int pos) {		
		if (pos==0) return 0;
		else return 1.0/(double)pos;
	}

	@Override
	public String method() {
		
		return "MRR method";
	}

}
