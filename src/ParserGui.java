import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParserGui extends JPanel implements ActionListener {

    private static JTextArea inputField = new JTextArea();
    private static JTextArea outputField = new JTextArea();
    private JButton runButton = new JButton("Run!");
    private JButton copyButton = new JButton("Copy!");
    private JButton clearButton = new JButton("Clear all!");
    private ClickListener runListener = new ClickListener();
    private ClickListener copyListener = new ClickListener();
    private ClickListener clearListener = new ClickListener();

    ParserGui() {
        makeGui();
    }

    private void makeGui() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        String clue1Text = "h==Header, p==Paragraph, s==Separator";
        String clue2Text = "ls==List Start, ll==List, le==List End";
        String clue3Text = "fl==Photo Left, fr==Photo Right, r==Reporter";
        String clue4Text = "sign==Signature, ss==Slider, sf==Slider Final";
        JLabel clue1 = new JLabel(clue1Text);
        JLabel clue2 = new JLabel(clue2Text);
        JLabel clue3 = new JLabel(clue3Text);
        JLabel clue4 = new JLabel(clue4Text);

        runButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        runButton.addActionListener(runListener);

        copyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        copyButton.addActionListener(copyListener);

        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        clearButton.addActionListener(clearListener);

        inputField.setLineWrap(true);
        inputField.setWrapStyleWord(true);

        JScrollPane inputFieldWithScroll = new JScrollPane(inputField);
        inputFieldWithScroll.setPreferredSize(new Dimension(500, 400));

        JScrollPane outputFieldWithScroll = new JScrollPane(outputField);
        outputFieldWithScroll.setPreferredSize(new Dimension(500, 200));

        panel.add(clue1);
        panel.add(clue2);
        panel.add(clue3);
        panel.add(clue4);
        panel.add(inputFieldWithScroll);
        panel.add(runButton);
        panel.add(copyButton);
        panel.add(outputFieldWithScroll);
        panel.add(clearButton);
        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
    }

    private class ClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == runButton) {
                outputField.setText("");
                ParserEngine.parseTextFromInputField();
            }

            if (e.getSource() == copyButton) {
                StringSelection stringSelection = new StringSelection(outputField.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }

            if (e.getSource() == clearButton) {
                inputField.setText("");
                outputField.setText("");
            }
        }
    }

    static JTextArea getInputField() {
        return inputField;
    }

    static JTextArea getOutputField() {
        return outputField;
    }
}
