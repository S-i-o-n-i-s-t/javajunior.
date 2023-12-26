package ru.geekbrains.lesson5;

public class Program {

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("@ABWETSRTBTRWB");
        String string = "@ABWE";
        stringBuilder.delete(string.length(), stringBuilder.length());
        String string2 = String.valueOf(stringBuilder);
        if (string.equals(string2)){
            System.out.println(stringBuilder);
        }
        boolean test = false;
        System.out.println(test);

        // stringBuilder.append();
//        char str = stringBuilder.charAt(0);
//        if (str != '@'){
//            System.out.println(str);
//        }
//        String aaa = "@ABWET";
//        int i = stringBuilder.indexOf(aaa);
//        stringBuilder.charAt(aaa.length());
//
//        if (i != -1) {
//
//            stringBuilder.delete(i, i + aaa.length());
//        }
//        System.out.println(stringBuilder);

    }

}
