package com.picoscott._0014;

public class Main {
    public static void main(String[] args) {
        new Solution().longestCommonPrefix(new String[]{"flower", "flow", "flight"});
    }
}

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String commonPrefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (!commonPrefix.isEmpty()) {
                if (strs[i].indexOf(commonPrefix) != 0) {
                    commonPrefix = commonPrefix.substring(0, commonPrefix.length() - 1);
                } else {
                    break;
                }
            }
        }
        return commonPrefix;
    }

    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            Character firstChar = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || firstChar != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private String longestCommonPrefix(String[] strs, int left, int right) {
        if (left == right) {
            return strs[left];
        } else {
            int middle = (left + right) / 2;
            String leftCommonPrefix = longestCommonPrefix(strs, left, middle);
            String rightCommonPrefix = longestCommonPrefix(strs, middle + 1, right);
            return commonPrefix(leftCommonPrefix, rightCommonPrefix);
        }
    }

    private String commonPrefix(String str1, String str2) {
        int minLength = Math.min(str1.length(), str2.length());
        for (int i = 0; i < minLength; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return str1.substring(0, i);
            }
        }
        return str1.substring(0, minLength);
    }

    public String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs)
            minLen = Math.min(minLen, str.length());
        int low = 1;
        int high = minLen;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle))
                low = middle + 1;
            else
                high = middle - 1;
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private boolean isCommonPrefix(String[] strs, int len) {
        String str1 = strs[0].substring(0, len);
        for (int i = 1; i < strs.length; i++)
            if (!strs[i].startsWith(str1))
                return false;
        return true;
    }
}