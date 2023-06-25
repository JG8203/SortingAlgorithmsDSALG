package com.dlsu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FileGenerator {

    private static final List<String> WORDS = Arrays.asList(
            "Gallant", "Einstein", "Wonderful", "Archimedes", "Strange", "Jackson", "Vigorous",
            "Keldysh", "Admiring", "Haslett", "Elated", "Shirley", "Gallant", "Kare"
    );

    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws IOException {
        generateFile("almostsorted10.txt", 10, true);
        generateFile("totallyreversed10.txt", 10, false);
        generateFile("random5.txt", 5, true);
        generateFile("random10.txt", 10, true);
        generateFile("random15.txt", 15, true);
        generateFile("random100.txt", 100, true);
    }

    private static void generateFile(String fileName, int numLines, boolean ascending) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(String.valueOf(numLines));
            writer.newLine();

            Integer[] numbers = new Integer[numLines];
            for (int i = 0; i < numLines; i++) {
                numbers[i] = RANDOM.nextInt(10000000);  // a random number between 0 and 10000000
            }

            Arrays.sort(numbers);

            if (!ascending) {
                Collections.reverse(Arrays.asList(numbers));
            }

            for (Integer number : numbers) {
                String line = number + " " + getRandomPhrase();
                writer.write(line);
                writer.newLine();
            }
        }
    }

    private static String getRandomPhrase() {
        return WORDS.get(RANDOM.nextInt(WORDS.size())) + " " + WORDS.get(RANDOM.nextInt(WORDS.size()));
    }
}
