package insomnia;

import java.util.Scanner;

/**
 * Created by akshay.mendole on 25/09/16.
 */
public class Saxena {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int t = scanner.nextInt();
        int[] balls = new int[100000];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            for (int j = 0; j < n; j++) {
                balls[j] = scanner.nextInt();
            }
            priintCheater(balls, n);
        }
    }

    private static void priintCheater(int[] balls, int n) {
        int i =0;
        if (n == 1){
            System.out.println("Not a proof");
        }
        for (i = 0; i < n; i++) {
            if(i>0 && balls[i-1] != balls[i] +1) {
                System.out.println("Cheater");
                return;
            }
        }
        System.out.println("Not a proof");
    }
}
