package org.anurag.inifinitestream1;

import java.math.BigInteger;

public class Solution {

  static class InfiniteStream {
    int[] bits;
    int currentPos; 

    public InfiniteStream(int[] bits) {
      this.bits = bits;
      this.currentPos = 0;
    }

    public int next() {
      if (currentPos >= bits.length) {
        throw new RuntimeException("Out of bounds");
      }

      return bits[currentPos++];
    }
  }

  public int findPattern(InfiniteStream infiniteStream, int[] pattern) {
    // Load pattern into patternBigInt
    BigInteger patternBigInteger = new BigInteger("0");
    int patternLen = pattern.length;
    BigInteger rollingBigInteger = new BigInteger("0");

    // Here the value in BigInteger could also be treated as a
    // rolling hash, even though in this case, since we're only
    // dealing with bits, the hash is the actual value.
    for (int i = 0; i < pattern.length; i++) {
      patternBigInteger = patternBigInteger.shiftLeft(1);
      if (pattern[i] == 1) {
        patternBigInteger = patternBigInteger.setBit(0);
      }

      rollingBigInteger = rollingBigInteger.shiftLeft(1);
      if (infiniteStream.next() == 1) {
        rollingBigInteger = rollingBigInteger.setBit(0);
      }
    }

    if (rollingBigInteger.equals(patternBigInteger)) {
      return 0;
    }

    // The problem definition states that the pattern is contained in the
    // input.
    int inputIndex = 1;
    while (true) {
      rollingBigInteger = rollingBigInteger.shiftLeft(1);
      if (infiniteStream.next() == 1) {
        rollingBigInteger = rollingBigInteger.setBit(0);
      }

      rollingBigInteger = rollingBigInteger.clearBit(patternLen);
     
      if (rollingBigInteger.equals(patternBigInteger)) {
        return inputIndex;
      }

      inputIndex++;
    }
  }

  public static void main(String[] args) {
    InfiniteStream infiniteStream = new InfiniteStream(new int[]{1,1,0,0,1,0});

    Solution solution = new Solution();

    System.out.println(solution.findPattern(infiniteStream, new int[]{0,0,1}));
  }  

}
