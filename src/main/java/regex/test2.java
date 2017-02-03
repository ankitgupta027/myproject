package regex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by akshay.mendole on 22/09/16.
 */
public class test2 {
    public static void main(String[] args) throws IOException {
        System.out.println(new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("cut -f2 /tmp/part-r-00000").getInputStream())).readLine());
    }
}
