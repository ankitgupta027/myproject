package insomnia;

/**
 * Created by akshay.mendole on 24/09/16.
 */
public class FindAllPrimeNumbers {
    public static void main(String[] args) {
        System.out.println(findNthPrime(4434));
    }
    public static int findNthPrime(int n){
        int k = 1;
        while((k*k - n) > 1 ||(n - k * k) > 1){
            k = (k + n / k) / 2;
        }
        return k;
    }
}
