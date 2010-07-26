package org.imirsel.mirex.eval.qbsh;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Result {
	public class Score {
		private double score;
		private String method;
		private double[] queryScores;
		private double[] groupScores;

		public Score(String method, double score, double[] queryScores,
				double[] groupScores) {
			this.method = method;
			this.score = score;
			this.groupScores = groupScores;
			this.queryScores = queryScores;
		}

		public String toString() {
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMinimumFractionDigits(3);
			return method + ":" + nf.format(score);
		}

		public double[] getQueryScores() {
			return queryScores;
		}

		public double[] getGroupScores() {
			return groupScores;
		}
	}

	private List<Score> scores;
	private int size;
	private String note;
	private String title;

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	public void addScore(Score score) {
		if (scores == null)
			scores = new ArrayList<Score>();
		scores.add(score);
	}

	public void addScore(String method, double score, double[] queryScores,
			double[] groupScores) {
		if (scores == null)
			scores = new ArrayList<Score>();
		scores.add(new Score(method, score, queryScores, groupScores));
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder(title + "\n");

		for (Score score : scores) {
			s.append(score + "\n");
		}
		s.append("total count:" + size + "\n");
		s.append("note:\n" + note + "\n");
		return s.toString();
	}

	public void printout(String basename)throws IOException{
		if (basename!=null){
			for (Score score:scores){
				Util.printScoreList(score.getGroupScores(),basename+"_group_"+score.method+".txt");
				Util.printScoreList(score.getQueryScores(),basename+"_query_"+score.method+".txt");
			
			}
		}
		
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void addNote(String addition) {
		if (this.note == null)
			note = addition;
		else
			note = note + addition;
	}

}
