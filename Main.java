import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create the base sounds
        BaseAbstractSound sound1 = new SineWaveSound(440, 10, 0);
        //BaseAbstractSound sound2 = new RampWaveSound(220, 6, 0, 0.7);
        //BaseAbstractSound sound3 = new SquareWaveSound(660, 4, 0, 0.5);

        // Create the composite sound
        List<BaseAbstractSound> waveforms = new ArrayList<>();
        waveforms.add(sound1);
        //waveforms.add(sound2);
        //waveforms.add(sound3);
        // Add more base sounds as needed...

        CompositeSound compositeSound = new CompositeSound(waveforms);

        // Play the composite sound
        SoundPlayer.playSound(compositeSound);
    }
}
