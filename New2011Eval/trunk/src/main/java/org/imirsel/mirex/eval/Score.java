package org.imirsel.mirex.eval;
/**
 * Generate scores from the position.
 * @author gzhu1
 *
 */
public interface Score {
  public double score(int pos);
  /**
   * 
   * @return proper formatted score method name
   */
  public String method();
}
