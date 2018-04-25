package lab14lib;
import edu.princeton.cs.introcs.StdAudio;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.AxesChartStyler;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class GeneratorAudioAnimator {
    private Generator generator;
    private XYChart chart;
    private JFrame frame;

    public GeneratorAudioAnimator(Generator generator) {
        this.generator = generator;
    }

    public void drawAndPlay(int numSamplesDraw, int numSamplesPlay) {
        chart = QuickChart.getChart("Wave Animator", "Time", "Value", "wave", new double[]{0}, new double[]{0});

        AxesChartStyler chartStyler = chart.getStyler();
        chartStyler.setYAxisMin(-1.0);
        chartStyler.setYAxisMax(1.0);
        chartStyler.setXAxisTicksVisible(false);

        frame = new JFrame("XChart");
        XChartPanel<XYChart> panel = new XChartPanel<>(chart);
        frame.add(panel);

        frame.pack();
        frame.setVisible(true);

        javax.swing.SwingUtilities.invokeLater(() -> {
            Animator animator = new Animator(numSamplesDraw, numSamplesPlay);
            animator.execute();
        });
    }

    private class Animator extends SwingWorker<Boolean, Double> {
        int numSamplesDraw;
        int numSamplesPlay;
        LinkedList<Double> samples = new LinkedList<>();

        private Animator(int numSamplesDraw, int numSamplesPlay) {
            this.numSamplesDraw = numSamplesDraw;
            this.numSamplesPlay = numSamplesPlay;
        }

        @Override
        protected Boolean doInBackground() throws Exception {
            while (!isCancelled()) {
                double sample = generator.next();
                StdAudio.play(sample);
                publish(sample);
            }
            return true;
        }

        @Override
        protected void process(List<Double> nextSamples) {
            for (Double sample : nextSamples) {
                samples.add(sample);
                if (samples.size() > numSamplesDraw) {
                    LinkedList<Double> oldSamples = samples;
                    chart.updateXYSeries("wave", null, oldSamples, null);
                    frame.repaint();
                    samples = new LinkedList<>();
                }
            }
        }
    }
}
