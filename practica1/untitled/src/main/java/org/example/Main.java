package org.example;

// Напишите программу, которая использует Stream API для обработки списка чисел. Программа должна вывести на экран среднее значение всех четных чисел в списке.

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println((Arrays.stream(createMassiv()).filter(item -> item %2 == 0).sum())/2);
       // Arrays.stream(createMassiv()).filter(item -> item %2 == 0);
        // System.out.println((Arrays.stream(createMassiv()).filter(item -> item %2 == 0).sum()/2));
    }
    public static int[] createMassiv(){
        int[] massiv = new int[100];
        for (int i = 0; i < 100; i++){
            massiv[i] = i;
        }
        return massiv;
    }
   // private static int[] genSquareMatrix(int matrixDimension) {
   //     int matrix[] = new int[100];
      //  Arrays.stream(matrix).forEachOrdered( x -> Arrays.stream(x));
      //  Arrays.stream(matrix).forEach(x->Arrays.stream(x).forEach(y-> y = generateDouble()));
    //    return matrix;
   // }
}