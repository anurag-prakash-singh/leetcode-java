package org.anurag.countzerorequestservers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCountZeroRequestServers {

    @Test
    public void test1() {
        Solution solution = new Solution();
        int[] result = solution.countServers(3, new int[][]{new int[]{1,3}, new int[]{2,6}, new int[]{1,5}}, 5, new int[]{10, 11});

        Assertions.assertArrayEquals(new int[]{1,2}, result);
    }

    @Test
    public void test2() {
        Solution solution = new Solution();
        int[] result = solution.countServers(3, new int[][]{new int[]{2,4}, new int[]{2,1}, new int[]{1,2}, new int[]{3,1}}, 2, new int[]{3,4});

        Assertions.assertArrayEquals(new int[]{0,1}, result);
    }

    @Test
    public void test3() {
        Solution solution = new Solution();
        int[] result = solution.countServers(4, new int[][]{new int[]{2,30}, new int[]{2,5}, new int[]{3,9}, new int[]{4,21}}, 9, new int[]{11});

        Assertions.assertArrayEquals(new int[]{2}, result);
    }

    @Test
    public void test4() {
        Solution solution = new Solution();
        int[] result = solution.countServers(6, new int[][]{new int[]{1,21}}, 10, new int[]{24,20});

        Assertions.assertArrayEquals(new int[]{5,6}, result);
    }

    @Test
    public void test5() {
        Solution solution = new Solution();
        int[] result = solution.countServers(6, new int[][]{new int[]{1,21}}, 10, new int[]{24,35,28,26,20,25,16,31,12});

        Assertions.assertArrayEquals(new int[]{5,6,5,5,6,5,6,5,6}, result);
    }
}
