import javax.sound.sampled.*;

public class SoundPlayer {
    private static volatile boolean isPlaying;

    public static void playSound(BaseAbstractSound sound) {
        isPlaying = true;

        try {
            // Set up audio format
            AudioFormat audioFormat = new AudioFormat(sound.calculateSampleRate(), sound.calculateSampleSize(), 1, true, false);
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);

            // Open the audio line
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            line.open(audioFormat);

            // Start playing the sound
            line.start();

            // Generate and write samples in a loop until playback is stopped
            while (isPlaying) {
                byte[] samples = sound.generateSamples();
                line.write(samples, 0, samples.length);
            }

            // Block until all the samples have been played
            line.drain();

            // Close the audio line
            line.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        isPlaying = false;
    }

    public static void stopSound() {
        isPlaying = false;
    }
}
