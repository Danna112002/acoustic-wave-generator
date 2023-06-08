public class SineWaveSound extends BaseAbstractSound {
    public SineWaveSound(double frequency, double duration, double dBamplitude) {
        super(frequency, duration, dBamplitude);
    }

    @Override
    public byte[] generateSamples() {
        int numSamples = (int) (calculateSampleRate() * duration);
        double angularFrequency = 2 * Math.PI * frequency / calculateSampleRate();
        byte[] samples = new byte[numSamples];
        double amplitude = calculateLinearAmplitude();
        for (int i = 0; i < numSamples; i++) {
            double value = amplitude * Math.sin(angularFrequency * i);
            samples[i] = (byte) (value * 127);
        }
        return samples;
    }
}