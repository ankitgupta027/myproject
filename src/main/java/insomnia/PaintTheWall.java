//package insomnia;
//
//import java.util.Scanner;
//
///**
// * Created by akshay.mendole on 24/09/16.
// */
//public class PaintTheWall {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int t = scanner.nextInt();
//        for (int i = 0; i < t; i++) {
//            int n = scanner.nextInt();
//            int[] arr = new int[n];
//            System.out.println(howMany(0 ,arr, n));
//        }
//    }
//
//    //WBR
//    //012
//    private static int howMany(int current, int[] arr, int n) {
//        int count = 0;
//
//        for (int i = current; i < n; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (current == n-1) {
//                    if (i == 0) {
//                        return 2;
//                    } else if(arr[i-1] == 1){
//                        return 2;
//                    } else {
//                        return 1;
//                    }
//                }
//                if(j == 1 && i!=n-1 && i!=0 && (arr[i-1] == 0 || arr[i-1] == 2)) {
//                    arr[i]=j;
//                    count += howMany(current+1, arr, n);
//                } else if (i==0 && j!= 1){
//                    arr[i] = j;
//                    count += howMany(current+1, arr, n);
//                } else if(i==0 && || arr[i-1] != j ) {
//
//                }
//
//            }
//        }
//        return count;
//    }
//}
