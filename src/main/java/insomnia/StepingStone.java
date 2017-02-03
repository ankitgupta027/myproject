package insomnia;

import java.util.Scanner;

/**
 * Created by akshay.mendole on 24/09/16.
 */
public class StepingStone {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print(getSorU(scanner.nextInt()) + " ");
        }
        //SSUSUUSUUUSUUUUS
        //123456789
    }

    private static String getSorU(int n) {
        if (n==1) {
            return "S";
        } else {
          //  n = n-1;
            int noUs = 1;
            int nextS = 2;
            while (nextS < n) {
                nextS = nextS + noUs +1 ;
                noUs = noUs +1;
            }
            if (nextS == n) {
                return "S";
            } else {
                return "U";
            }
        }
    }

}
