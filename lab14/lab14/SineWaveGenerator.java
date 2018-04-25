package lab14;

import edu.princeton.cs.algs4.StdAudio;
import lab14lib.Generator;

public class SineWaveGenerator implements Generator {
	private double frequency;
	private int state;	

	public SineWaveGenerator(double frequency) {
		state = 0;
		this.frequency = frequency;
	}

	public double next() {
		state = (state + 1);
		double period = StdAudio.SAMPLE_RATE / frequency;
		return Math.sin(state * 2 * Math.PI / period);
	}
}

/* How it works (requires some EE16 knowledge): Sample rate is 44100 Hz.
 * 
 * So that means for a 440 Hz note, we want our sine wave to go through
 * 100.2272 samples. By default, sin has a period of 2*pi. So our 0th
 * sample should correspond to t = 0, out 1st sample should correspond to
 * 2*pi/100.2272, our 2nd sample should be 2*pi/100.2272 * 2, ...
 * up to 2*pi/100.2272 * 100. 
 */