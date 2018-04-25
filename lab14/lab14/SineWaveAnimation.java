package lab14;

import lab14lib.Generator;
import lab14lib.GeneratorAudioAnimator;

public class SineWaveAnimation {
    public static void main(String[] args) {
        Generator generator = new SineWaveGenerator(440);
        GeneratorAudioAnimator ga = new GeneratorAudioAnimator(generator);
        ga.drawAndPlay(500,400000);
    }
}
