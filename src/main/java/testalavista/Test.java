package testalavista;

/**
 * Created by akshay.mendole on 18/11/16.
 */
public class Test {
    public static void main(String[] args) {
        String jobName = "ALL_USERS_12322";
        System.out.println(jobName.substring(0, jobName.lastIndexOf("_")));
    }
}
