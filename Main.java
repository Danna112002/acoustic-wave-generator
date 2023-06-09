import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SineWaveSound sineWaveSound = new SineWaveSound(440, 4, 0);

        // Create the waveforms for the composite sound
        //List<BaseAbstractSound> waveforms = new ArrayList<>();
        //waveforms.add(new SineWaveSound(440, 2, 0)); // Sine waveform
        //waveforms.add(new RampWaveSound(220, 2, 0, 0.7)); // Ramp waveform
        //waveforms.add(new SquareWaveSound(660, 2, 2, 0.5)); // Square waveform
        // Add more waveforms as needed...

        // Create the composite sound
        //CompositeSound compositeSound = new CompositeSound(waveforms);

        // Play the composite sound
        SoundPlayer.playSound(sineWaveSound);
    }
}
