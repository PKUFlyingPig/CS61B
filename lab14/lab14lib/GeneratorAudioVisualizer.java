package lab14lib;
import edu.princeton.cs.introcs.StdAudio;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class GeneratorAudioVisualizer {
	private Generator generator;

	public GeneratorAudioVisualizer(Generator generator) {
		this.generator = generator;
	}

	public void drawAndPlay(int numSamplesDraw, int numSamplesPlay) {
		double[] xValues = new double[numSamplesDraw];
		double[] samples = new double[numSamplesDraw];
		for (int ii = 0; ii < numSamplesDraw; ii += 1) {
			xValues[ii] = ii;
			samples[ii] = generator.next();
		}

		// Create Chart
	    XYChart chart = QuickChart.getChart("Generator Output", "X", "Y", "y(x)", xValues, samples);
	 
	    // Show it
	    new SwingWrapper<>(chart).displayChart();

	    for (int jj = 0; jj < numSamplesDraw; jj += 1) {
	    	StdAudio.play(samples[jj]);
	    }	

	    int remainingSamples = numSamplesPlay - numSamplesDraw;
	    for (int jj = 0; jj < remainingSamples; jj += 1) {
	    	StdAudio.play(generator.next());
	    }
		
	}
} 