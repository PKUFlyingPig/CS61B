package lab14lib;
import java.util.ArrayList;
import java.util.Collection;

public class MultiGenerator implements Generator {
	private ArrayList<Generator> generators;

	public MultiGenerator(Collection<Generator> gs) {
		generators = new ArrayList<Generator>();
		generators.addAll(gs); 
	}

	public double next() {
		int N = generators.size();
		double total = 0;
		for (Generator g : generators) {
			total += g.next();
		}
		total = total / N;
		return total;
	}
} 