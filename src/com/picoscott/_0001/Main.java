package com.picoscott._0001;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int target = 9;
        int[] nums = new int[]{2, 7, 11, 15};
        System.out.println(Arrays.toString(new Solution().twoSum3(nums, target)));
    }
}

class Solution {
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (i != j && nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[2];
    }

    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> cacheMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            cacheMap.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (cacheMap.containsKey(target - nums[i])) {
                return new int[]{i, cacheMap.get(target - nums[i])};
            }
        }
        return new int[2];
    }

    public int[] twoSum3(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[1] = i;
                result[0] = map.get(target - nums[i]);
            }
            map.put(nums[i], i);
        }
        return result;
    }
}