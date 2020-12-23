package ee.bcs.valiit.tasks;

import ee.bcs.valiit.tasks.exercises.Lesson1MathUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lesson1MathUtilTest {

    @Test
    public void min() {
        assertEquals(2, Lesson1MathUtil.min(2, 3));
    }

    @Test
    public void  max_simple(){
        assertEquals(5,Lesson1MathUtil.max(2,5));
    }

    @Test
    public void abs_negative(){
        assertEquals(6,Lesson1MathUtil.abs(-6));
    }

    @Test
    public void compare_doubles(){
        double a = 1.0;
        double b=1.000001;
        assertEquals(a,b, 0.001);
    }

}
