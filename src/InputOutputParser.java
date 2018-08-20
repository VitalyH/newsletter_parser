import javax.swing.*;
import java.awt.*;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * InputOutputParser class implements...
 * ...to be continued
 */

public class InputOutputParser extends JPanel {

    /**
     * Constructor
     */
    InputOutputParser() {
        DrawUI();
    }

    /**
     * Create UI
     */
    private void DrawUI() {
        // JPanel with box layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Labels
        JLabel inputLabel = new JLabel("Input");
        JLabel outputLabel = new JLabel("Output");

        // Button
        JButton button = new JButton("Go!");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input field
        JTextArea inputField = new JTextArea();
        inputField.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputField.setPreferredSize(new Dimension(500, 500));

        // Output field
        JTextArea outputField = new JTextArea();
        outputField.setPreferredSize(new Dimension(500, 500));
        outputField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add all components
        panel.add(inputLabel);
        panel.add(inputField);
        panel.add(button);
        panel.add(outputLabel);
        panel.add(outputField);
        add(panel);
    }

}

