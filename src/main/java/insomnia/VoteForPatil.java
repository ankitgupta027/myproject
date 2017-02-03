package insomnia;

import java.util.*;

/**
 * Created by akshay.mendole on 24/09/16.
 */
public class VoteForPatil {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int dep = scanner.nextInt();
        List<Integer> students =  new ArrayList<>(9999);

        for (int i = 0; i < dep; i++) {
            students.add(scanner.nextInt());
        }

        Collections.sort(students);

        int minvotes = 0;
        for (int i = 0; i < getMajority(dep); i++) {
            minvotes = minvotes + getMajority(students.get(i));
            int dfi = 1;
        }
        System.out.println(minvotes);
    }

    public static int getMajority(int n ){
        return n/2+1;
    }

}
