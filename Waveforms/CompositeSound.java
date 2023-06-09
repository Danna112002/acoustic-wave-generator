import java.util.List;

public class CompositeSound extends BaseAbstractSound {
    private List<BaseAbstractSound> waveforms;

    public CompositeSound(List<BaseAbstractSound> waveforms) {
        super(0, 0, 0); // Set initial values to 0 as they are not used

        this.waveforms = waveforms;
        calculateDurationAndAmplitude();
    }

    private void calculateDurationAndAmplitude() {
        double maxDuration = 0;
        double maxAmplitude = 0;

        for (BaseAbstractSound waveform : waveforms) {
            double waveformDuration = waveform.getDuration();
            double waveformAmplitude = waveform.getdBamplitude();

            if (waveformDuration > maxDuration) {
                maxDuration = waveformDuration;
            }
            if (waveformAmplitude > maxAmplitude) {
                maxAmplitude = waveformAmplitude;
            }
        }

        setDuration(maxDuration);
        setdBamplitude(maxAmplitude);
    }

    @Override
    public byte[] generateSamples() {
        int numSamples = (int) (calculateSampleRate() * getDuration());
        byte[] samples = new byte[numSamples];

        for (BaseAbstractSound waveform : waveforms) {
            byte[] waveformSamples = waveform.generateSamples();

            for (int i = 0; i < numSamples; i++) {
                if (i < waveformSamples.length) {
                    samples[i] += waveformSamples[i];
                }
            }
        }

        return samples;
    }
}
