package com;

public class TestMain {

    public static void main(String[] args) {
        int number = 1234;
        int number2 = 10000;
        int number3 = -10000;
        System.out.println(sumDigits(number));
        System.out.println(sumDigits(number2));
        System.out.println(sumDigits(number3));
    }

    public static int sumDigits(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number can't be negative!");
        }
        String numberAsString = Integer.toString(number);
        char[] myArray = numberAsString.toCharArray();
        int[] numbersArray = new int[myArray.length];
        int sumNumbers = 0;
        for (int i = 0; i < myArray.length; i++) {
             numbersArray [i] = Character.getNumericValue(myArray[i]);
             sumNumbers += numbersArray[i];
        }
        return sumNumbers;
    }
}
