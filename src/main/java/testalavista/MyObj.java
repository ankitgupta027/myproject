package testalavista;

import org.apache.htrace.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Created by akshay.mendole on 17/11/16.
 */
public class MyObj {
    String a;
    String b;

    private String c = "fd";

    @JsonSerialize(using=JsonDateSerializer.class)
    Date date;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
