package util;

public class Transform {
    public static String intToBinaryString(int integer) {
        return String.format("%16s", Integer.toBinaryString(integer)).replace(" ", "0");
    }

    public static String intToBinaryString(int bitsNumber, int integer) {
        String temp = "%" + bitsNumber + "s";
        return String.format(temp, Integer.toBinaryString(integer)).replace(" ", "0");
    }

    public static void main(String[] args) {
        int i = 10;
        System.out.println(String.format("%16s", Integer.toBinaryString(i)).replace(" ", "0"));
    }

    public static int binaryToDecimal(String binary) {
        return Integer.valueOf(binary, 2);

    }
}
