import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MathUtilsTest
{
    MathUtilsTest.MathUtils mathUtils = new MathUtils();

    @Test
    public void testAddition()
    {
        assertEquals(10, mathUtils.add(5, 5), "5 + 5 should be 10");
    }

    @Test
    public void testSubstractions()
    {
        assertEquals(3, mathUtils.subtract(5,2), "5 - 2 should be 3");
    }

    @Test
    public void testMultiplication()
    {
        assertEquals(20, mathUtils.multiply(4, 5), "4 * 5 should be 20");
    }

    @Test
    public void testDivision() {
        assertEquals(2.5, mathUtils.divide(5, 2), "5 / 2 should be 2.5");
    }

    @Test
    public void testDivisionByZero()
    {
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(5, 0), "Division by 0 throws an Arithmetic Exception");
    }

    @Test
    public void testIsEven()
    {
        assertTrue(mathUtils.isEven(18), "18 should be an even number");
        assertTrue(mathUtils.isEven(88), "88 should be an even number");
        assertTrue(mathUtils.isEven(2), "2 should be an even number");
    }


    public class MathUtils
    {
        public int add(int a, int b)
        {
            return a + b;
        }

        public int subtract(int a, int b)
        {
            return a - b;
        }

        public int multiply(int a, int b)
        {
            return a * b;
        }

        public double divide(int a, int b)
        {
            if (b == 0)
            {
                throw new ArithmeticException("Cannot divide by zero");
            }
            return (double) a / b;
        }

        public boolean isEven(int num)
        {
            return num % 2 == 0;
        }
    }

}


