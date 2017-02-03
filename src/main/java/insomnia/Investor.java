package insomnia;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by akshay.mendole on 25/09/16.
 */
public class Investor {

    static Map<Integer, Integer> investorMap1 = new HashMap();
    static Map<Integer, Integer> investorMap3= new HashMap();
    static Map<Integer, Integer> investorMap9= new HashMap();

    static int finalRank = -2;
    static int investorNumer = -9;
    public static int getSumOfDigits(int num)
    {
        int sum = 0;

        while (num > 0)
        {
            sum = sum + num % 10;
            num = num / 10;
        }
        return sum;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        createHashmap(investorMap1, 1, 1);
        createHashmap(investorMap3, 3, 1);
        createHashmap(investorMap9, 9, 1);
        System.out.println(investorMap1.size() +  " " + investorMap3.size());
//        int n = scanner.nextInt();
//        int i =0;
//        do{
//            i++;
//            System.out.println("Case #"+i);
//            int sum  = getMatchedValue(n);
//            System.out.println("first meets investor " + investorNumer + " at " +sum) ;
//        } while (n!= 0);
    }

    private static int getMatchedValue(int n) {
        int rank1 =-1, rank3 = -1, rank9 =-1;
        if (investorMap1.get(n) != null)
            rank1 = investorMap1.get(n);
        if (investorMap3.get(n) != null)
            rank3 = investorMap3.get(n);
        if (investorMap9.get(n) != null)
            rank9 = investorMap9.get(n);

        int rank = getLesserRank(rank1, rank3, rank9);
        if (rank == -1) {
            return getMatchedValue(n+ getSumOfDigits(n));
        } else {
            if (rank == rank1  && rank1 != -1)  {
                finalRank = rank1;
                investorNumer =1;
            } else if(rank == rank3  && rank3 != -1)  {
                finalRank = rank3;
                investorNumer =2;
            } else if(rank == rank9  && rank9 != -1)  {
                finalRank = rank9;
                investorNumer=3;
            }
            return n;
        }
    }

    private static int getLesserRank(int rank1, int rank3, int rank9) {
        if(rank1 <= rank3 && rank1 <=rank9) {
            return rank1;
        } else if(rank3 <= rank1 && rank3 <=rank9) {
            return rank3;
        }else {
            return rank9;
        }
    }

    private static Map<Integer,Integer> createHashmap(Map<Integer, Integer> investorMap , int i, int rank) {
        int newSum = getSumOfDigits(i) + i;
        if (investorMap.get(newSum) == null) {
            investorMap.put(newSum, rank);
            return createHashmap(investorMap, newSum,  rank+1);
        } else {
            return investorMap;
        }
    }
}
