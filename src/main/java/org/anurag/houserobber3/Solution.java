/**
 * https://leetcode.com/problems/house-robber-iii/
 */

package org.anurag.houserobber3;

import java.util.Hashtable;

public class Solution {

    private Hashtable<TreeNode, int[]> cache = new Hashtable<>();

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * MaxValue for tree rooted at root.
     *
     * @param root
     * @return Array such that array[0] is maxValue with root included and array[1] is maxValue with root excluded.
     */
    int[] maxValue(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        if (cache.containsKey(root)) {
            return cache.get(root);
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        // Include root
        int maxValueLeftIncluded = maxValue(left)[0];
        int maxValueLeftNotIncluded = maxValue(left)[1];
        int maxValueRightIncluded = maxValue(right)[0];
        int maxValueRightNotIncluded = maxValue(right)[1];

        int maxValWithLeftWithRight = maxValueLeftIncluded + maxValueRightIncluded;
        int maxValWithLeftWithoutRight = maxValueLeftIncluded + maxValueRightNotIncluded;
        int maxValWithoutLeftWithRight = maxValueLeftNotIncluded + maxValueRightIncluded;
        int maxValWithoutLeftWithoutRight = maxValueLeftNotIncluded + maxValueRightNotIncluded;

        int maxValueWithoutRoot = Integer.max(Integer.max(maxValWithLeftWithRight, maxValWithLeftWithoutRight),
                Integer.max(maxValWithoutLeftWithRight, maxValWithoutLeftWithoutRight));

        // Exclude root
        int maxValueWithRoot = root.val + maxValueLeftNotIncluded + maxValueRightNotIncluded;

        int[] maxVal = new int[]{maxValueWithRoot, maxValueWithoutRoot};

        cache.put(root, maxVal);

        return maxVal;
    }

    public int rob(TreeNode root) {
        int[] maxValuesWithAndWithoutRoot = maxValue(root);
        return Integer.max(maxValuesWithAndWithoutRoot[0], maxValuesWithAndWithoutRoot[1]);
    }

}
