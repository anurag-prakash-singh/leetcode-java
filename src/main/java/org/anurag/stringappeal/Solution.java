package org.anurag.stringappeal;

// Intuition:
// For each index 'i' in the string, calculate the total appeal of all substrings ending at 'i'.
// So appeals[i] = appeals[i - 1] + X
// We now need to determine what X should be. That is, we need to determine how to take s[i] into
// consideration. There are two possibilities for s[i]:
// Case 1: We have not seen s[i] before:
//  - in this case, there are i substrings ending at index i - 1
//  - to each substring, we can tack on s[i]. In addition, we need to consider s[i] by itself.
//  - Therefore, appeals[i] = appeals[i - 1] + i + 1
// Case 2: We have seen s[i] at the index 'j':
//  - the only substrings for which s[i] will add any new appeals are the ones that don't end in s[j].
//  - Therefore, appeals[i] = appeals[i - 1] + (i - j)

class Solution {
    public long appealSum(String s) {
        int[] appeals = new int[s.length()];
        int[] lastSeenArr = new int[26];

        for (int i = 0; i < lastSeenArr.length; i++) {
             lastSeenArr[i] = -1;
        }

        lastSeenArr[s.charAt(0) - 'a'] = 0;
        appeals[0] = 1;
        
        // We've already considered the first element so initilize appeals[0] as 1.
        long totalAppeals = 1;

        for (int i = 1; i < s.length(); i++) {
            int lastSeenIndex = lastSeenArr[s.charAt(i) - 'a'];

            appeals[i] = appeals[i - 1] + (i - lastSeenIndex);
            totalAppeals += appeals[i];
            lastSeenArr[s.charAt(i) - 'a'] = i;
        }

        return totalAppeals;
    }
}
