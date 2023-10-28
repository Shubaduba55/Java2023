/*
* Classname - Main
*
* Version info...
*
* Programmer(s): Ivan Shamkov (KPI, FICT, IP-11)
*
* Date: 07.09.2023
*
* Copyright notice...
*
* Description:
* */

import java.util.Scanner; // For User Input

public class Main {
    public static void main(String[] args){
        int CONSTANT = 1;
        double totalSum = 0;
        boolean isDenominatorZero = false;
        double nominator;
        double denominator;
        Scanner myScanner = new Scanner(System.in);

        System.out.print("Input a: ");
        int a = myScanner.nextInt();
        System.out.print("Input b: ");
        int b = myScanner.nextInt();
        System.out.print("Input n: ");
        int n = myScanner.nextInt();
        System.out.print("Input m: ");
        int m = myScanner.nextInt();

        for(double i = a; i <= n && !isDenominatorZero; i++){
            for (double j = b; j <= m && !isDenominatorZero; j++){
                nominator = i-j;
                denominator = i+CONSTANT;

                if (denominator == 0){
                    isDenominatorZero = true;
                }else{
                totalSum += nominator / denominator;
                }
            }
        }

        if (isDenominatorZero){
            System.out.println("Error, denominator is zero");
        } else{
            System.out.println("Summation result is: " + totalSum);
        }
    }
}
