public abstract class BaseAbstractSound {

    protected double frequency;   // Frequency of the sound in Hz(??)
    protected double dBamplitude; // loudness of the sound in dB
    


    public BaseAbstractSound(double frequency, double dBamplitude) {
        this.frequency = frequency;
        this.dBamplitude = dBamplitude;   
    }

    //public abstract void play(); //abstract method to play the sound
    public abstract byte[] generateSamples(); //abstract method to generate samples of chosen waveform


    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getdBamplitude() {
        return dBamplitude;
    }

    public void setdBamplitude(double dBamplitude) {
        this.dBamplitude = dBamplitude;
    }

    // Calculate the sample rate based on duration
    protected int calculateSampleRate() {
        int desiredSampleRate = 44100; // Default sample rate
        return desiredSampleRate;
    }

    protected int calculateSampleSize() {
        int desiredSampleSize = 8; // Default sample size in bits
        return desiredSampleSize;
    }


     // conversion between amplitude in decibels to linear scale (dunno if necessary)
     protected double calculateLinearAmplitude() {
        double linearAmpitude = Math.pow(10, dBamplitude/20);

        //return linearAmpitude*(Math.pow(2, calculateSampleSize() -1) -1);
        return linearAmpitude;
    }

}
    

