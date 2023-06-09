import java.util.ArrayList;
import java.util.List;

public class CompositeSound extends BaseAbstractSound {
    private List<BaseAbstractSound> waveforms;

    public CompositeSound(List<BaseAbstractSound> waveforms) {
        super(0, 0, 0); // Placeholder values, not used in composite sound
        this.waveforms = waveforms;

        // Calculate the total duration of the composite sound
        for (BaseAbstractSound waveform : waveforms) {
            duration = Math.max(duration, waveform.getDuration());
        }
    }

    @Override
    public byte[] generateSamples() {
        List<Thread> threads = new ArrayList<>();
        List<byte[]> waveformSamples = new ArrayList<>();

        // Create a thread for each waveform
        for (BaseAbstractSound waveform : waveforms) {
            Thread thread = new Thread(() -> {
                byte[] samples = waveform.generateSamples();
                synchronized (waveformSamples) {
                    waveformSamples.add(samples);
                }
            });
            threads.add(thread);
            thread.start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Combine the samples from each waveform
        int totalSamples = waveformSamples.stream().mapToInt(arr -> arr.length).sum();
        byte[] compositeSamples = new byte[totalSamples];
        int currentIndex = 0;
        for (byte[] samples : waveformSamples) {
            System.arraycopy(samples, 0, compositeSamples, currentIndex, samples.length);
            currentIndex += samples.length;
        }

        return compositeSamples;
    }
}
