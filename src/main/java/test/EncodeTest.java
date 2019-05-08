package test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodeTest {
    public static BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    public static void main(String [] args){
        String str="name";
        System.out.println(encoder.encode(str));
        System.out.println(encoder.encode(str));
        System.out.println(encoder.encode(str));
    }

}
