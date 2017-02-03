package testalavista;


import org.apache.htrace.fasterxml.jackson.core.JsonProcessingException;
import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

/**
 * Created by akshay.mendole on 21/08/16.
 */
public class JacksonTest {
    public static void main(String[] args) throws JsonProcessingException {
        String str = "f";
        ObjectMapper objectMapper = new ObjectMapper();
        MyObj myObj = new MyObj();
        myObj.setA("aa");
        myObj.setB("bb");
      //  myObj.c = "hello";
        myObj.setDate(new Date(System.currentTimeMillis()));
        System.out.println(objectMapper.writeValueAsString(myObj));

    }
}
