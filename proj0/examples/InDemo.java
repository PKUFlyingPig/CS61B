/** Lets the user request the yearly salt production
 *  in metric tons of a given country! Fun fun fun!
 *  @author Josh Hug
 */

public class InDemo {	

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please supply a country as a command line argument.");
			System.out.println("For countries with spaces, use an underscore, e.g. South_Korea");
			/* NOTE: Please don't use System.exit() in your code.
			   It will break the autograder. */
			System.exit(0);
		}	

		/* Start reading in national_salt_production.txt */
		In in = new In("national_salt_production.txt");
		
		/* Keep looking until the file is empty. */
		while(!in.isEmpty()) {
			/* Each line has the rank of a country, then its
			 * name, then its production in metric tons, and
			 * finally the fraction of world salt output it produces. */
			int rank = in.readInt();
			String name = in.readString();
			int tons = in.readInt();
			double fractionProduced = in.readDouble();
			if (name.equals(args[0])) {
				System.out.println(args[0] + " produces " +
					               tons + " of salt per year. Very nice!");
			}			
		}
	}
} 