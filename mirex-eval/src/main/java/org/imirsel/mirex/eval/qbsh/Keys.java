package org.imirsel.mirex.eval.qbsh;

public interface Keys {
	public String getAnswer(String query);
	public void init(String query);
	public int getQueryNumber();
	public int getAnswerNumber();
	public int getQueryId(String query);
	public int getAnswerIdByQuery(String query);
	public int getAnswerIdByAnswer(String answer);
	
}
