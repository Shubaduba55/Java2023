/*
 * Classname - Main
 *
 * Version info...
 *
 * Programmer(s): Ivan Shamkov (KPI, FICT, IP-11)
 *
 * Date: 04.11.2023
 *
 * Copyright notice...
 *
 * Description:
 * Main class used for implementation of Lab 3.
 * C3 = 34 % 3 = 1 (StringBuffer)
 * C17 = 34 % 17 = 0 (Знайти найбільшу кількість речень заданого тексту, в яких є однакові слова.)
 * */


import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

// For reading from .txt
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    static public void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        StringBuffer text = new StringBuffer(scanner.next());

        String textInputPath = "Text.txt";
        StringBuffer text = new StringBuffer();
        BufferedReader reader = new BufferedReader(new FileReader(textInputPath));
        String line;

        while ((line = reader.readLine()) != null) {
            text.append(line);
        }

        System.out.println(text);

//      Init splitters
        char[] sentenceChars = new char[]{'.', '!', '?'};
        char[] wordChars = {' '};

//      Split text into sentences
        StringBuffer[] fullSentences = mySplit(text, sentenceChars);

//      Array to store text: sentence by row, word by column

        ArrayList<StringBuffer>[] splitSentences = new ArrayList[fullSentences.length];
        for (int i = 0; i < splitSentences.length; i++) {
            splitSentences[i] = new ArrayList<>();
        }

        StringBuffer[] words;

        for (int i = 0; i < fullSentences.length; i++){
            words = mySplit(fullSentences[i], wordChars);

            for (StringBuffer w : words)
                splitSentences[i].add(w);
        }

//      Print text from ArrayList
        for (int i = 0; i < fullSentences.length; i++){
            for (StringBuffer w : splitSentences[i])
                System.out.print(w + " ");
            System.out.println();
        }

    }

    static boolean isContained(char[] characters, char character)
    {
        boolean contained = false;

        for (char c : characters) {
            if (c == character) {
                contained = true;
                break;
            }
        }

        return contained;
    }

    static int countSplitElements(StringBuffer input, char[] splitChars) {
        int count = 0;
        boolean isLast;
        boolean isFirst;
        int length = input.length();

        // TODO: Rework IsFirst
        for (int i = 0; i < length; i++) {
            isLast = i+1 == length;
            isFirst = i == 0;
            // We don't want to add the split at the end of the sequence,
            // because we will count it anyway.

            if (isContained(splitChars, input.charAt(i)) && !isLast && !isFirst)
                count++;
        }

        // The number of splits is less than the amount of actual splitting parts.
        return count + 1;
    }

    static StringBuffer[] mySplit(StringBuffer input, char[] splitChars) {

        int splitNumber = countSplitElements(input, splitChars);

        StringBuffer[] result = new StringBuffer[splitNumber];
        int nextElementId = 0;

        StringBuffer element = new StringBuffer();
        char character;
        boolean isLast;
        int length = input.length();

        for (int i = 0; i < length; i++){
            character = input.charAt(i);
            isLast = i+1 == length;

            if (!isContained(splitChars, character)){
                element.append(character);

                // Skip split if not last
                if(!isLast){
                    continue;
                }
            }

            if (!element.isEmpty()){
                result[nextElementId] = new StringBuffer(element);
                nextElementId++;
                // Make our element empty
                element.setLength(0);
            }
        }

        return result;
    }
}
