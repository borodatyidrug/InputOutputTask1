package inputoutputtask1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

public class InputOutputTask1 {
    
    static final String DIR_PREFIX = "/home/aurumbeats/games";
    
    public static void logger(StringBuilder log, String message) {
        log.append(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.MEDIUM, FormatStyle.MEDIUM)));
        log.append(" : ");
        log.append(message);
        log.append("\n");
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        StringBuilder log = new StringBuilder();
        List<File> tree = new ArrayList<>();
        
        tree.add(new File(DIR_PREFIX + "/src"));
        tree.add(new File(DIR_PREFIX + "/src/main"));
        tree.add(new File(DIR_PREFIX + "/src/main/Main.java"));
        tree.add(new File(DIR_PREFIX + "/src/main/Utils.java"));
        tree.add(new File(DIR_PREFIX + "/src/test"));
        tree.add(new File(DIR_PREFIX + "/res"));
        tree.add(new File(DIR_PREFIX + "/res/drawables"));
        tree.add(new File(DIR_PREFIX + "/res/vectors"));
        tree.add(new File(DIR_PREFIX + "/res/icons"));
        tree.add(new File(DIR_PREFIX + "/savegames"));
        tree.add(new File(DIR_PREFIX + "/temp"));
        tree.add(new File(DIR_PREFIX + "/temp/temp.txt"));
        
        tree.stream()
                .forEach(file -> {
                    try {
                        if (file.exists()) {
                            logger(log, "File or directory already exists.");
                        } else {
                            if (file.getName().contains(".")) {
                                file.createNewFile();
                                logger(log, "Created new file " + file.getPath());
                            } else {
                                file.mkdir();
                                logger(log, "Created new directory "
                                        + file.getPath());
                            }
                        }
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                });
        try (FileWriter fw
                // файл для лога - последний в списке
                = new FileWriter(tree.get(tree.size() - 1), true)) {
            fw.append(log.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }    
}
