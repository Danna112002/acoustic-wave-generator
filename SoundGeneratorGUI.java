import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SoundGeneratorGUI extends JFrame {
    private JTextField frequencyTextField;
    private JTextField amplitudeTextField;
    private JTextField proportionTextField;
    private JTextField dutyFactorTextField;
    private JComboBox<String> soundOptionsComboBox;
    private volatile boolean isPlaying;

    public SoundGeneratorGUI() {
        // Set up the JFrame
        setTitle("Acoustic Sound Generator");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Create the components
        JLabel soundOptionsLabel = new JLabel("Sound Options");
        soundOptionsComboBox = new JComboBox<>(new String[]{"Sine Wave", "Ramp Wave", "Square Wave"});
        JLabel frequencyLabel = new JLabel("Frequency (Hz)");
        frequencyTextField = new JTextField();
        JLabel amplitudeLabel = new JLabel("Amplitude");
        amplitudeTextField = new JTextField();
        JLabel proportionLabel = new JLabel("Proportion");
        proportionTextField = new JTextField();
        JLabel dutyFactorLabel = new JLabel("Duty Factor");
        dutyFactorTextField = new JTextField();

        // Add components to the JFrame
        add(soundOptionsLabel);
        add(soundOptionsComboBox);
        add(frequencyLabel);
        add(frequencyTextField);
        add(amplitudeLabel);
        add(amplitudeTextField);
        add(proportionLabel);
        add(proportionTextField);
        add(dutyFactorLabel);
        add(dutyFactorTextField);

        // Add a listener to the sound options combo box
        soundOptionsComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) soundOptionsComboBox.getSelectedItem();

               // Enable/disable the text fields based on the selected option
               if (selectedOption.equals("Ramp Wave")) {
                proportionTextField.setEnabled(true);
                dutyFactorTextField.setEnabled(false);
            } else if (selectedOption.equals("Sine Wave")) {
                proportionTextField.setEnabled(false);
                dutyFactorTextField.setEnabled(false);
            } else if (selectedOption.equals("Square Wave")) {
                proportionTextField.setEnabled(false);
                dutyFactorTextField.setEnabled(true);
            }
            }
        });

        // Create the play button
        JButton playButton = new JButton("Play");
        // Create the stop button
        JButton stopButton = new JButton("Stop");

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve the entered values
                double frequency = Double.parseDouble(frequencyTextField.getText());
                double amplitude = Double.parseDouble(amplitudeTextField.getText());
                String selectedOption = (String) soundOptionsComboBox.getSelectedItem();

                // Disable the play button and enable the stop button
                playButton.setEnabled(false);
                stopButton.setEnabled(true);
                isPlaying = true;

                // Create a separate thread for sound generation and playback
                Thread soundThread = new Thread(new Runnable() {
                    public void run() {
                        // Generate and play the sound based on the selected option
                        switch (selectedOption) {
                            case "Sine Wave":
                                SineWaveSound sineWave = new SineWaveSound(frequency, amplitude);
                                SoundPlayer.playSound(sineWave);
                                break;
                            case "Ramp Wave":
                                double proportion = Double.parseDouble(proportionTextField.getText());
                                RampWaveSound rampWave = new RampWaveSound(frequency, amplitude, proportion);
                                SoundPlayer.playSound(rampWave);
                                break;
                            case "Square Wave":
                                double dutyFactor = Double.parseDouble(dutyFactorTextField.getText());
                                SquareWaveSound squareWave = new SquareWaveSound(frequency, amplitude, dutyFactor);
                                SoundPlayer.playSound(squareWave);
                                break;
                        }

                        // Re-enable the play button and disable the stop button
                        playButton.setEnabled(true);
                        stopButton.setEnabled(false);
                        isPlaying = false;
                    }
                });

                // Start the sound thread
                soundThread.start();
            }
        });

        stopButton.setEnabled(false);
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Stop the sound playback by calling stopSound method
                SoundPlayer.stopSound();

                // Disable the stop button
                stopButton.setEnabled(false);
            }
        });

        // Add the play button and stop button to the JFrame
        add(playButton);
        add(stopButton);

        // Display the JFrame
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SoundGeneratorGUI();
            }
        });
    }
}
