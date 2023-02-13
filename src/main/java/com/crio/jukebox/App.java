package com.crio.jukebox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.appConfig.ApplicationConfig;



public class App {
    // To run the application ./gradlew run --args="INPUT-FILE=jukebox-input.txt"
    public static void main(String[] args) {
        List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        String expectedSequence = "INPUT-FILE";
        String actualSequence =
                commandLineArgs.stream().map(a -> a.split("=")[0]).collect(Collectors.joining("$"));
        if (expectedSequence.equals(actualSequence)) {
            run(commandLineArgs);
        }
    }

    public static void run(List<String> commandLineArgs) {
        // Complete the final logic to run the complete program.
        ApplicationConfig applicationConfig = new ApplicationConfig();
        String inputFile = commandLineArgs.get(0).split("=")[1];
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            line = reader.readLine();
            while (line != null) {
                List<String> tokens = new ArrayList(Arrays.asList(line.split(" ")));
                applicationConfig.getCommandInvoker().executeCommand(tokens.get(0), tokens);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
    }
}
