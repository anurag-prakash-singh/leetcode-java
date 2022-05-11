package org.anurag.houserobber3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.anurag.houserobber3.Solution.*;

public class TestHouseRobber3 {

    @Test
    void test1() {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(1);

        node1.left = node2; node1.right = node3;
        node2.left = node4; node2.right = node5;
        node3.right = node6;

        Solution solution = new Solution();

        Assertions.assertEquals(9, solution.rob(node1));
    }

    @Test
    void test2() {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(8);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(1);
        TreeNode node7 = new TreeNode(2);

        node1.left = node2; node1.right = node3;
        node2.left = node4; node2.right = node5;
        node3.left = node6; node3.right = node7;

        Solution solution = new Solution();

        Assertions.assertEquals(17, solution.rob(node1));
    }
}
