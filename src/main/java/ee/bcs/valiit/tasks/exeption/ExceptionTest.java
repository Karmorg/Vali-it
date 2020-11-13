package ee.bcs.valiit.tasks.exeption;

public class ExceptionTest {
    public static int exceptionTest(Integer i) {
        return fib(i);
    }

    public static int fib(Integer i) {
        if (i < 1) {
            throw new ApplicationException("i peab olema suurem kui 0");
        }
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        return fib(i - 1) + fib(i - 2);
    }
}
