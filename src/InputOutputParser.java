import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;


/**
 * InputOutputParser class implements text parser for
 * Newsletter, "100 Years 100 Days" project, sliders inside CMS
 */

public class InputOutputParser extends JPanel implements ActionListener {

    // ActionListener
    public void actionPerformed(ActionEvent e) {
    }

    // Input and output fields
    private JTextArea inputFieldNoScroll = new JTextArea();
    private JTextArea outputFieldNoScroll = new JTextArea();

    // Buttons with listeners
    private JButton runButton = new JButton("Run!");
    private JButton clearButton = new JButton("Clear all!");
    private ClickListener runListener = new ClickListener();
    private ClickListener clearListener = new ClickListener();


    /**
     * Constructor
     */
    InputOutputParser() {
        DrawUI();
    }

    /**
     * UI
     */
    private void DrawUI() {
        // JPanel with box layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Labels
        String clue1Text = "h==Header, p==Paragraph, s==Separator";
        String clue2Text = "ls==List Start, ll==List, le==List End";
        String clue3Text = "fl==Photo Left, fr==Photo Right, r==Reporter";
        String clue4Text = "sign==Signature, ss==Slider, sf==Slider Final";
        JLabel clue1 = new JLabel(clue1Text);
        JLabel clue2 = new JLabel(clue2Text);
        JLabel clue3 = new JLabel(clue3Text);
        JLabel clue4 = new JLabel(clue4Text);

        // "Run" button
        runButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        runButton.addActionListener(runListener);

        // "Clear" button
        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        clearButton.addActionListener(clearListener);

        // Input field
        // With text wrap
        inputFieldNoScroll.setLineWrap(true);
        inputFieldNoScroll.setWrapStyleWord(true);
        // Add scroll
        JScrollPane inputField = new JScrollPane(inputFieldNoScroll);
        inputField.setPreferredSize(new Dimension(500, 400));

        // Output field with scroll
        JScrollPane outputField = new JScrollPane(outputFieldNoScroll);
        outputField.setPreferredSize(new Dimension(500, 200));

        // Add all components
        panel.add(clue1);
        panel.add(clue2);
        panel.add(clue3);
        panel.add(clue4);
        panel.add(inputField);
        panel.add(runButton);
        panel.add(outputField);
        panel.add(clearButton);
        add(panel);
    }


    private class ClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            // Run parser by clicking on the "Run" button
            if (e.getSource() == runButton) {
                newsletterParser();
            }

            // Clear all by clicking on the "Clear all!" button
            if (e.getSource() == clearButton) {
                inputFieldNoScroll.setText("");
                outputFieldNoScroll.setText("");
            }

        }
    }


    /**
     * Main parser
     */
    private void newsletterParser() {
        // Variables to work with
        //String line;        // Line of text with markers
        String cleanLine;   // Line of text w/o markers
        // A code snippets for newsletter
        String div = "</div></div>";
        String spanLi = "</span></li>";
        String spanLiUlEnd = "</span></li></ul></div>";
        String header = "<div data-change=\"tr\"><div align=\"left\" class=\"title\" style=\"padding: 38px 0 28px; line-height: 36px; font-size: 31px; font-weight: 300; color: #171313!important; font-family: 'Roboto', Arial, sans-serif;\" data-change=\"td\" colspan=\"2\" valign=\"middle\">";
        String paragraph = "<div data-change=\"tr\"><div class=\"td\" style=\"padding-bottom: 35px; color: #777777; font-size: 16px; font-weight: 300; line-height: 22px; font-family: 'Roboto', Arial, sans-serif;\" data-change=\"td\" colspan=\"2\">";
        String separator = "<div data-change=\"tr\"><div class=\"divider\" data-change=\"td\" colspan=\"2\"><div style=\"width: 333px; height: 2px; background-color: #d9d9d9;\"></div></div></div>";
        String listStart = "<div data-change=\"tr\"><div class=\"td\" style=\"padding-bottom: 35px; color: #777777; font-size: 16px; font-weight: 300; line-height: 22px; font-family: 'Roboto', Arial, sans-serif;\" data-change=\"td\" colspan=\"2\"> <ul class=\"ul\" style=\"margin: 0; padding: 0 0 0 40px\"> <li class=\"li\" style=\"color: #171313; padding: 5px 11px;\"><span style=\"color: #777777\">";
        String list = "<li class=\"li\" style=\"color: #171313; padding: 5px 11px;\"><span style=\"color: #777777\">";
        String reporter = "<div class=\"include\" data-change=\"tr\">[include]/www/htdocs/rudelfi/includes/newsletter2018/reporter.inc[/include]</div>";
        String fotoLeftStart = "<div data-change=\"tr\"><div class=\"td\" valign=\"top\" colspan=\"2\" style=\"padding-bottom: 35px; position: relative; color: #777777; font-size: 16px; font-weight: 300; line-height: 22px; font-family: 'Roboto', Arial, sans-serif; overflow:auto;\" data-change=\"td\"> <div class=\"text-first\" style=\"width: 412px; float: right;\">";
        String fotoLeftEnd = "</div><div class=\"image-first\" style=\"position: relative; width: 397px; float: left;\"><img width=\"397\" height=\"221\" style=\"display: block;\" src=\"http://placehold.it/397x221\"><div class=\"img-author\" style=\"height: 34px; padding: 0 9px; line-height: 34px; font-size: 12px; font-weight: 400; background-color: #f7f7f7; color: #616161;\">Фото: ???</div></div></div>";
        String fotoRightStart = "<div data-change=\"tr\"><div class=\"td\" style=\"padding-bottom: 35px; position: relative; color: #777777; font-size: 16px; font-weight: 300; line-height: 22px; font-family: 'Roboto', Arial, sans-serif; overflow:auto;\" data-change=\"td\" colspan=\"2\" valign=\"top\"><div class=\"text-first inverse-text\" style=\"width: 412px; float: left;\">";
        String fotoRightEnd = "</div><div class=\"image-first inverse-image\" style=\"position: relative; width: 397px; float: right;\"><img width=\"397\" height=\"221\" style=\"display: block;\" src=\"http://placehold.it/397x221\"><div class=\"img-author\" style=\"height: 34px; padding: 0 9px; line-height: 34px; font-size: 12px; font-weight: 400; background-color: #f7f7f7; color: #616161;\">Фото: ??????</div></div>";
        String signStart = "<div data-change=\"tr\"><div style=\"overflow:auto;\" data-change=\"td\" colspan=\"2\"> <img width=\"60\" height=\"60\" class=\"img-writer\" style=\"float: left; width: 60px; height: 60px; margin: 0 22px 43px 62px; display: inline-block!important; vertical-align: top;\" alt=\"name surname\" src=\"http://g1.delphi.lv/wd/f/9723/4QE6UW_vitaalijs.jpeg\"><div class=\"img-desc\" style=\"float: left; display: inline-block!important; vertical-align: top; color: #777777; font-size: 15px; font-family: 'Roboto', Arial, sans-serif;\">Ваш, <br>";
        String signEnd = "<br><b style=\"color: #171313\">Виталий Хлапковский</b></div>";
        // Bold and web-links
        String originalBoldStart = "<strong>";
        String newBoldStart = "<b style=\"color: #171313\">";
        String originalBoldEnd = "</strong>";
        String newBoldEnd = "</b>";
        String originalWebLinkStart = "<a href=\"";
        String newWebLinkStart = "<a style=\"color: #0099ff; text-decoration: none\" href=\"";
        // Dashes and quotes
        String shortDash = " - ";
        String extraShortDash = " – ";
        String doubleDash = "--";
        String longDash = " — ";
        String guillemetOpen = "«";
        String guillemetClose = "»";
        String quoteToReplace = "\"";
        String crookedQuoteOpen = "“";
        String crookedQuoteClose = "”";
        // "Sliders"
        String sliderStart = "<!--page_toc type=\"list\" tabs_style=\"titles\"--><!--page_break title=\"";
        String sliderEnd = "\" tab_title=\"\"-->";
        String sliderFinal = "<!--page_toc type=\"list\" tabs_style=\"titles\"-->";

        // Clear output field
        outputFieldNoScroll.setText("");

        // Process the input file
        for (String line : inputFieldNoScroll.getText().split("\\n")) {

            // Remove extra whitespaces
            line = line.trim();

            // Skip empty lines and lines which don't need modification
            if (!line.isEmpty() && line.contains("==")) {

                // Split the string
                String tempArray[] = line.split("==");

                // Clean the string from the marker
                if (tempArray.length > 1) {
                    cleanLine = tempArray[1];
                } else {
                    cleanLine = "";
                }

                // Check if line have needed marker
                // Add the code accordingly
                switch (tempArray[0]) {
                    // Newsletter cases
                    case "h":
                        cleanLine = header + cleanLine + div;
                        break;
                    case "p":
                        cleanLine = paragraph + cleanLine + div;
                        break;
                    case "s":
                        cleanLine = separator;
                        break;
                    case "ls":
                        cleanLine = listStart + cleanLine + spanLi;
                        break;
                    case "ll":
                        cleanLine = list + cleanLine + spanLi;
                        break;
                    case "le":
                        cleanLine = list + cleanLine + spanLiUlEnd;
                        break;
                    case "r":
                        cleanLine = reporter;
                        break;
                    case "fl":
                        cleanLine = fotoLeftStart + cleanLine + fotoLeftEnd;
                        break;
                    case "fr":
                        cleanLine = fotoRightStart + cleanLine + fotoRightEnd;
                        break;
                    case "sign":
                        cleanLine = signStart + cleanLine + signEnd;
                        break;
                    case "ss":
                        cleanLine = sliderStart + cleanLine + sliderEnd;
                        break;
                    case "sf":
                        cleanLine = sliderFinal;
                        break;
                }

            } else {
                // Keep empty and unmodified lines intact
                cleanLine = line;

            }

            // Replace dashes, "bold" text, web-links, quotes inside the string
            cleanLine = cleanLine.replace(originalBoldStart, newBoldStart)
                    .replace(originalBoldEnd, newBoldEnd)
                    .replace(originalWebLinkStart, newWebLinkStart)
                    .replace(guillemetOpen, quoteToReplace)
                    .replace(guillemetClose, quoteToReplace)
                    .replace(crookedQuoteOpen, quoteToReplace)
                    .replace(crookedQuoteClose, quoteToReplace)
                    .replace(shortDash, longDash)
                    .replace(extraShortDash, longDash)
                    .replace(doubleDash, longDash);

            // Write result into the output field
            outputFieldNoScroll.append(cleanLine + "\n");
        }

    }
}

/**
 * File-based parser, used in the v.1.
 * For future reference
 */
//    final private static String INPUT_FILE = "input.txt";
//    final private static String OUTPUT_FILE = "output.txt";
//    private void fileParser() {
//        // I/O operations
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE)));
//             BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
//
//            // Variables to work with
//            String line;        // Line of text with markers
//            String cleanLine;   // Line of text w/o markers
//
//            // A code snippets for newsletter
//
//            // Bold and web-links
//
//            // Dashes and quotes
//
//            // "Sliders"
//
//            // Process the input file
//            while ((line = br.readLine()) != null) {
//
//                // Remove extra whitespaces
//                line = line.trim();
//
//                // Skip empty lines and lines which don't need modification
//                if (!line.isEmpty() && line.contains("==")) {
//
//                    // Split the string
//                    String tempArray[] = line.split("==");
//
//                    // Clean the string from the marker
//                    cleanLine = tempArray[1];
//
//                    // Check if line have needed marker
//                    // Add the code accordingly
//                    switch (tempArray[0]) {
//                        // Newsletter cases
//                        case "h":
//                            cleanLine = header + cleanLine + div;
//                            break;
//
//                    }
//
//                } else {
//                    // Keep empty and unmodified lines intact
//                    cleanLine = line;
//
//                }
//
//                // Replace dashes, "bold" text, web-links, quotes inside the string
//                cleanLine = cleanLine.replace(originalBoldStart, newBoldStart);
//
//                // Write result into the output file
//                writer.write(cleanLine);
//                writer.newLine();
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


