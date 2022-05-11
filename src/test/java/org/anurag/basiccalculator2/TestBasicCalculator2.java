package org.anurag.basiccalculator2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TestBasicCalculator2 {

    @Test
    void testGetTokens1() {
        Solution solution = new Solution();

        String str = "  1 + 2- 3 +-4- -5 * 6";
        List<String> expectedTokens = Arrays.asList("1","+","2","-","3", "+", "-4","-", "-5", "*", "6");
        List<String> result = solution.getTokens(str);

//        result.forEach(System.out::println);
        Assertions.assertArrayEquals(expectedTokens.toArray(), result.toArray());
    }

    @Test
    void testGetTokens2() {
        Solution solution = new Solution();

        String str = "4--5";
        str = str.replace(" ", "");
        List<String> expectedTokens = Arrays.asList("4", "-", "-5");
        List<String> result = solution.getTokens(str);

        Assertions.assertArrayEquals(expectedTokens.toArray(), result.toArray());
    }

    @Test
    void testCalculate1() {
        Solution solution = new Solution();
        String[] ss = {
                "1 + 2 - 3",
                "5 + 2 * 4",
                "5 * 2 * 5",
                "5 * -2 * 5",
                "10 / -2 * 5",
                " 3+5 / 2 ",
                "-5 / 2 ",
                "10",
                "1-1+1",
                "0-2",
        };
        int[] expected = {
                0,
                13,
                50,
                -50,
                -25,
                5,
                -2,
                10,
                1,
                -2
        };

        for (int i = 0; i < ss.length; i++) {
            Assertions.assertEquals(expected[i], solution.calculate(ss[i]));
        }
    }
}
