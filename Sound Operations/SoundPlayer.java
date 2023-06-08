import javax.sound.sampled.*;

public class SoundPlayer {
    public static void playSound(BaseAbstractSound sound) {
        byte[] samples = sound.generateSamples();
        int sampleRate = sound.calculateSampleRate();
        int sampleSize = sound.calculateSampleSize();

        try {
            // Set up audio format
            AudioFormat audioFormat = new AudioFormat(sampleRate, sampleSize, 1, true, false);
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);

            // Open the audio line
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            line.open(audioFormat);

            // Start playing the sound
            line.start();

            // Write the samples to the audio line
            line.write(samples, 0, samples.length);

            // Block until all the samples have been played
            line.drain();

            // Close the audio line
            line.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
