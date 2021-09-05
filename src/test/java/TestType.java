import org.junit.Test;

public class TestType {
    @Test
    public void test() {
        Object[] arr = new Object[10];
        arr[0] = 1;
        arr[1] = 3.14f;
        System.out.println(arr[0] instanceof Integer);
        System.out.println(arr[1] instanceof Float);
        System.out.println(arr[0] instanceof Float);
    }
}
