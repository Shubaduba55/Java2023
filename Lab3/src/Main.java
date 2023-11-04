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



import java.util.Scanner;

public class Main {
    static public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuffer text = new StringBuffer(scanner.nextLine());

        char[] sentenceChars = {'!', '.', '?'};

        StringBuffer[] sentences = mySplit(text, sentenceChars);

        System.out.println(text);

        for (StringBuffer sentence : sentences){
            System.out.println(sentence);
            char[] wordChars = {' '};
            StringBuffer[] splitSentence = mySplit(sentence, wordChars);
            for (StringBuffer word : splitSentence){
                System.out.println(word);
            }

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
        for (int i = 0; i < input.length(); i++) {
            if (isContained(splitChars, input.charAt(i)))
                count++;
        }
        return count;
    }

    static void copyStringBuffer(StringBuffer dest, StringBuffer source){
        if (dest != null) dest.setLength(0);

        for(int i = 0; i < source.length(); i++){
            dest.append(source.charAt(i));
        }
    }

    static StringBuffer[] mySplit(StringBuffer input, char[] splitChars) {
        int splitNumber = countSplitElements(input, splitChars);

        StringBuffer[] result = new StringBuffer[splitNumber];
        int nextElementId = 0;

        StringBuffer element = new StringBuffer();
        char character;


        for (int i = 0; i < input.length(); i++){
            character = input.charAt(i);


            //  || input.charAt(i+1) == '\0'
            if (!isContained(splitChars, character)){
                element.append(character);
            } else {
                // copyStringBuffer(result[nextElementId], element);

                result[nextElementId] = new StringBuffer();
                result[nextElementId].append(element);

                nextElementId++;
                // Make our element empty
                element.setLength(0);
            }
        }

        return result;
    }


}
