public class Main {
    public static void main(String[] args) {
        // Define the parameters for the sine wave sound
        double frequency = 440; // Frequency of A4 note (in Hz)
        double duration = 10.0; // Duration of the sound (in seconds)
        double dBamplitude = 0.0; // Amplitude of the sound in decibels

        // Create an instance of the SineWaveSound
        SineWaveSound sineWaveSound = new SineWaveSound(frequency, duration, dBamplitude);

        // Play the sound
        sineWaveSound.play();
    }
}
