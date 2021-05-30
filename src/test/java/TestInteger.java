import org.junit.Test;

import java.math.BigInteger;

public class TestInteger {
    @Test
    public void test() {
        String s = "0034";
        System.out.println(Integer.parseInt(s, 16));
        char[] chars = {'0','0','3','4'};
        System.out.println(new String(chars));
    }

    @Test
    public void test2() {
        System.out.println(new BigInteger("a0f0a4387a3bb342", 16).longValue());
    }

    @Test
    public void test3() {

    }
}
