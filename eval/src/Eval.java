import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Evaluation for QBSH/QBT
 * 
 * @author gzhu1
 *
 */
public class Eval {

	/**
	 * Starting method for the evaluation 
	 * Running Command:Eval resultFile outputFile
	 * Example Eval result2010.qbsh.txt output.txt
	 * 
	 * Careful about the format in the resultFile.  
	 * It also generates all the detailed result files in the main directory. 
	 * @param args
	 */

	public static void main(String[] args) {

		EvalFile ef;
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"mirex.xml");
		// ef=(EvalFile)context.getBean("simpleSingleEvalFile");

		if (args.length > 1) {
			Scanner s = null;
			BufferedReader reader = null;
			PrintWriter out = null;
			try {
				reader = new BufferedReader(new FileReader(args[0]));
				out = new PrintWriter(args[1]);
				String str;
				while ((str = reader.readLine()) != null) {
					s = new Scanner(str);
					s.useDelimiter("\\s*\\|\\|\\s*|\\s*\\n|\\s*\\r");
					s.next();
					String type = s.next();
					ef = (EvalFile) context
							.getBean(type.toLowerCase() + "Eval");
					// if (type.toUpperCase().equals("QBT"))ef=new QbtEval();
					// else if (type.toUpperCase().equals("QBSH"))ef=new
					// QbshEval();
					String basename=type+"_"+s.next()+"_"+s.next();
					if (ef != null) {
						Result result = ef.evalResult(str.trim());
						out.println(result.toString());
						result.printout(basename);
					} else {
						out.println("wrong line:" + str);
					}

					// System.out.println(fileName+"::::"+comment);
				}
			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				if (s != null) {
					s.close();
				}
				if (out != null) {
					out.close();
				}
			}
		} else {
			System.out.print("Not enough args: Eval inputFile outputFile");
		}

	}

}
