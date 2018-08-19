package com.picoscott._0007;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().reverse1(2147483609));
    }
}

class Solution {
    public int reverse(int x) {
        int rev = 0;
        int tmpMax = Integer.MAX_VALUE / 10;
        int tmpMin = Integer.MIN_VALUE / 10;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > tmpMax || (rev == tmpMax && pop > 7)) {
                return 0;
            }
            if (rev < tmpMin || (rev == tmpMin && pop > 8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public int reverse1(int x) {
        long rev = 0;
        while (x != 0) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) ? 0 : (int) rev;
    }
}
