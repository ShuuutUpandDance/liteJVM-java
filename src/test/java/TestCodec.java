import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

public class TestCodec {
    @Test
    public void test() {
        String foo = "I am a string";
        byte[] bytes = foo.getBytes();
        System.out.println( Hex.encodeHexString( bytes ) );
    }
}
