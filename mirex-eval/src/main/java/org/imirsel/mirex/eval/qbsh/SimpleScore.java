package org.imirsel.mirex.eval.qbsh;

public class SimpleScore implements Score {

	@Override
	public double score(int pos) {
		
		if (pos==0) return 0;
		else return 1.0;
	}

	@Override
	public String method() {
		
		return "Simple Score Method";
	}
	

}
