import javax.swing.*;

public class Main {

    private static void createAndShowGUI() {
        //Create and set up the window
        JFrame frame = new JFrame("N-letter parser");

        // What happens when the frame closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add content to the window
        frame.add(new InputOutputParser());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(Main::createAndShowGUI);
    }
}

