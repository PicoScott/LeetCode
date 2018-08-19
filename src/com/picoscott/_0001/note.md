# 问题

Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

### Example:

```java
Given nums = [2, 7, 11, 15], target = 9,
Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
```



# 解决方案

### 方法一：

遍历数组，将任意两个数字相加比较其结果是否为所需要的`target`值

```java
for (int i = 0; i < nums.length; i++) {
    for( int j = i + 1; j < nums.length; j++){
        if( i != j && nums[i] + nums [j] == target){
            return new int[]{i, j};
        }
    }
}
return new int[2];
```

##### 复杂度分析

- 时间复杂度：`O(n^2) ` 。在此方式中我们尝试遍历nums数组(`O(n)`)并与任意其它数字相加(`O(n-1)`)与`target`结果进行比较，并去除了位置调换顺序的比较(注：`j = i + 1`)。因此时间复杂度=`O(n * (n -1) / 2)`=`O(n^2)`
- 空间复杂度：`O(1)`。



### 方法二：

方法一中使用了两个`for`循环解决了该问题，那么有时间复杂度更低的方法吗？可以遍历每个元素，并将其值-索引存入哈希表中，并在遍历时从哈希表中检查是否存在期望的值`target - nums[i]`。

```java
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
```

上边的方法中具有一定优化空间，我们不必遍历`nums`数组创建哈希表，可以在遍历数据时查看哈希表中是否有期望的值，如果没有可以将当前的值-索引加入哈希表中(注：期望的数值总是成对的出现在数组中，因此我们可以在第一个出现时将其加入至哈希表中，在第二个出现时查找到)。

```java
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
```

实例分析：我们遍历至2时哈希表中没有对应的期望值7，可以将2加入哈希表中；当遍历至7时哈希表查找到哈希表中存在2，因此可以将`[0, 1]`返回，注意：我们查找到的结果的顺序总是与期望值相反，因此需要在返回时调整顺序。

##### 复杂度分析

- 时间复杂度：`O(n)`。 使用哈希表将查找期望值从`O(n)`降至`O(1)`，因此时间复杂度为`O(n)`
- 空间复杂度：`O(n)`。使用哈希表存储为查找到期望值的数据，其中存储了`n`个数据，因此空间复杂度为`O(n)`