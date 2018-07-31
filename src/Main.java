import java.io.*;

public class Main {

    final private static String INPUT_FILE = "input.txt";
    final private static String OUTPUT_FILE = "output.txt";

    public static void main(String[] args) {

        // I/O operations
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE)));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {

            String line;        // Line of text with markers
            String cleanLine;   // Line of text w/o markers
            // A code snippets to insert in output file
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

            // Process the input file
            while ((line = br.readLine()) != null) {

                // Correctly skip empty lines and lines with whitespaces
                line = line.trim();
                if (!line.isEmpty()) {

                    // Split the string
                    String tempArray[] = line.split("==");

                    // Clean the string from the marker
                    cleanLine = tempArray[1];

                    // Check if line has needed marker
                    // Add the code accordingly
                    switch (tempArray[0]) {
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
                    }
                    // Write result into the output file
                    writer.write(cleanLine);
                    writer.newLine();

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

