public class SquareWaveSound extends BaseAbstractSound {
    private double dutyFactor; // Duty factor of the square waveform

    public SquareWaveSound(double frequency, double dBamplitude, double dutyFactor) {
        super(frequency, dBamplitude);
        this.dutyFactor = dutyFactor;
    }

    @Override
    public byte[] generateSamples() {
        int numSamples = calculateSampleRate(); // Generate samples for continuous playback
        double period = calculateSampleRate() / frequency;
        byte[] samples = new byte[numSamples];
        double amplitude = calculateLinearAmplitude();
        int highSamples = (int) (period * dutyFactor);

        for (int i = 0; i < numSamples; i++) {
            samples[i] = (byte) ((i % period) < highSamples ? amplitude : -amplitude);
        }
        return samples;
    }
}