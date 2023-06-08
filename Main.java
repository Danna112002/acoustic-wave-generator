public class Main {
    public static void main(String[] args) {
        // Create instances of different waveform sounds
        //SineWaveSound sineWave = new SineWaveSound(440, 2, 0);
        //RampWaveSound rampWave = new RampWaveSound(440, 2, 0);
        SquareWaveSound squareWave = new SquareWaveSound(440, 2, 0);

        // Play the selected waveform sound
        //SoundPlayer.playSound(sineWave);
        //SoundPlayer.playSound(rampWave);
        SoundPlayer.playSound(squareWave);
    }
}