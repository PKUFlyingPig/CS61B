package lab14lib;
import edu.princeton.cs.introcs.StdAudio;

public class GeneratorPlayer {
	private Generator generator;

	public GeneratorPlayer(Generator generator) {
		this.generator = generator;
	}

	public void play(int numSamples) {
		for (int ii = 0; ii < numSamples; ii += 1) {
			StdAudio.play(generator.next());		
		}
	}

}