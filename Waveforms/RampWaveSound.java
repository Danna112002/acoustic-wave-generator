public class RampWaveSound extends BaseAbstractSound {
    public RampWaveSound(double frequency, double duration, double dBamplitude) {
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
        for (int i = 0; i < numSamples; i++) {
            samples[i] = (byte) (value * 127);
            value += increment;
            if (value > amplitude)
                value = -amplitude;
        }
        return samples;
    }
}