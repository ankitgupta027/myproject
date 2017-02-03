package insomnia;
import java.util.Scanner;

/**
 * Created by akshay.mendole on 24/09/16.
 */
public class Cypher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            String inputStr = scanner.next();
            int n = scanner.nextInt();
            System.out.println(gimmeCypher(findNthPrime(n), inputStr));
        }
    }


    public static String gimmeCypher(int n, String str){
        StringBuffer stringBuffer = new StringBuffer();
        n = n%26;
        char[] strarr = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (strarr[i] + n +1 > 122) {
                strarr[i] = (char)(97 + strarr[i] + n - 122);
            } else {
                strarr[i] = (char) (strarr[i] + n + 1);
            }
        }
        return new String(strarr);
    }

    public static int findNthPrime(int n){
        int num, count, i;
        num=1;
        count=0;

        while (count < n){
            num=num+1;
            int root = (int)Math.sqrt(num);
            for (i = 2; i <= root; i++){
                if (num % i == 0) {
                    break;
                }
            }
            if ( i == num){
                count = count+1;
            }
        }
        return num;
    }
}
