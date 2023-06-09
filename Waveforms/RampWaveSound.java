public class RampWaveSound extends BaseAbstractSound {
    private double proportion;

    public RampWaveSound(double frequency, double duration, double dBamplitude, double proportion) {
        super(frequency, duration, dBamplitude);
    }

    @Override
    public byte[] generateSamples() {
        int numSamples = (int) (calculateSampleRate() * duration);
        double period = calculateSampleRate() / frequency;
        byte[] samples = new byte[numSamples];
        double amplitude = calculateLinearAmplitude();
        double increment = amplitude * 2 / period;
        double value = -amplitude;
        int switchIndex = (int) (numSamples * proportion);
        for (int i = 0; i < numSamples; i++) {
            samples[i] = (byte) (value * 127);
            value += increment;
            if (i == switchIndex) {
                increment = -increment; // Switch the increment direction
            } 
        }
        return samples;
    }
}