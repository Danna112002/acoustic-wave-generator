public class SineWaveSound extends BaseAbstractSound {

    public SineWaveSound(double frequency, double duration, double dBamplitude) {
        super(frequency, duration, dBamplitude);
    }

    @Override
    public void play() {
        // Generate sound samples
        byte[] samples = generateSineWaveSamples();

        // Use SoundPlayer for playback
        SoundPlayer.playSound(samples, calculateSampleRate(), calculateSampleSize());
    }

    private byte[] generateSineWaveSamples() {
        // Calculate the total number of samples based on the sample rate and duration
    int numSamples = (int) (calculateSampleRate() * duration);

    // Calculate the angular frequency (2πf) of the sine wave
    double angularFrequency =   2 *Math.PI * frequency/calculateSampleRate();



    // Create a byte array to hold the generated samples
    byte[] samples = new byte[numSamples];
    double amplitude = calculateLinearAmplitude(); // Calculate the linear amplitude
    // Generate the samples for the sine wave
    for (int i = 0; i < numSamples; i++) {        

        // Calculate the value of the sine wave at the current time
        double value = amplitude * Math.sin(angularFrequency * i);

        // Convert the value to a byte representation (assuming 8-bit samples)
        samples[i] = (byte) (value * 127); // Scale the value to the range -128 to 127
    }


    return samples;
}
}
