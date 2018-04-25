package lab14;

import lab14lib.Generator;
import java.util.ArrayList;
import lab14lib.GeneratorAudioVisualizer;
import lab14lib.MultiGenerator;

public class PlayMajorChord {
    public static void main(String[] args) {
        Generator g1 = new SineWaveGenerator(523.25);
        Generator g2 = new SineWaveGenerator(659.25);
        Generator g3 = new SineWaveGenerator(783.99);

        ArrayList<Generator> generators = new ArrayList<Generator>();
        generators.add(g1);
        generators.add(g2);
        generators.add(g3);
        MultiGenerator mg = new MultiGenerator(generators);

        GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(mg);
        gav.drawAndPlay(1200, 100000);
    }
}
