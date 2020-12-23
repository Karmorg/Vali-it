package ee.bcs.valiit.tasks;

import ee.bcs.valiit.tasks.exercises.Lesson2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lesson2Test {

    @Test
    public void exercise2(){
        int ans[] = {2,4,6,8};
        Assertions.assertArrayEquals(ans, Lesson2.exercise2(4));
    }
}
