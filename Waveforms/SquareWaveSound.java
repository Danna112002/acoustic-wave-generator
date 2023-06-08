public class SquareWaveSound extends BaseAbstractSound {
    public SquareWaveSound(double frequency, double duration, double dBamplitude) {
        super(frequency, duration, dBamplitude);
    }

    @Override
    public byte[] generateSamples() {
        int numSamples = (int) (calculateSampleRate() * duration);
        double period = calculateSampleRate() / frequency;
        byte[] samples = new byte[numSamples];
        double amplitude = calculateLinearAmplitude();
        for (int i = 0; i < numSamples; i++) {
            samples[i] = (byte) ((i % period) < (period / 2) ? -amplitude : amplitude);
        }
        return samples;
    }
}