import java.util.List;

public class CompositeSound extends BaseAbstractSound {
    private List<BaseAbstractSound> waveforms;

    public CompositeSound(List<BaseAbstractSound> waveforms) {
        super(0, 0, 0); // Set initial values to 0 as they are not used
        this.waveforms = waveforms;
        calculateDuration();
    }

    private void calculateDuration() {
        double totalDuration = 0;


        for (BaseAbstractSound waveform : waveforms) {
            double waveformDuration = waveform.getDuration();
            totalDuration += waveformDuration;
        }

        double meanDuration = totalDuration / waveforms.size();

        setDuration(meanDuration);
    }

    @Override
    public byte[] generateSamples() {
        int numSamples = (int) (calculateSampleRate() * getDuration());
        byte[] samples = new byte[numSamples];

        // Create an array of threads
        Thread[] threads = new Thread[waveforms.size()];

        for (int i = 0; i < waveforms.size(); i++) {
            final int index = i;

            threads[i] = new Thread(() -> {
                BaseAbstractSound waveform = waveforms.get(index);
                byte[] waveformSamples = waveform.generateSamples();

                for (int j = 0; j < numSamples; j++) {
                    if (j < waveformSamples.length) {
                        samples[j] += waveformSamples[j];
                    }
                }
            });

            threads[i].start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return samples;
    }
}
