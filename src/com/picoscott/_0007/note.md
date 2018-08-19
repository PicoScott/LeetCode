# 7. Reverse Integer

## 问题

Given a 32-bit signed integer, reverse digits of an integer.

**Example 1:**

```
Input: 123
Output: 321
```

**Example 2:**

```
Input: -123
Output: -321
```

**Example 3:**

```
Input: 120
Output: 21
```

**Note:**
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

## 解决方案

### 方法一：

数字反转，可以考虑将数字变为字符串，并将字符串反转后转化为字符串即可，但此题中不允许使用该方式。

使用数学方式也可以考虑用此方式，获取最后一位数字将其加入至`rev`中，并将原来的数字除以10

```java
int pop = x % 10;
x /= 10;
rev = rev * 10 + pop;
```

然而`rev = rev * 10 + pop`可能会产生溢出，因此我们需要进行边界检查，以下两种情况会发生溢出

1. 如果 `rev > Integer.MAX_VALUE / 10` 或 `rev == Integer.MAX_VALUE / 10 && pop > 7`
2. 如果 `rev < Integer.MIN_VALUE / 10`或 `rev == Integer.MIN_VALUE && pop > 8`

````java
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
````

检查边界时可能晦涩难懂，更简单的方式：将`rev`使用`long`进行存储，转换后的数据不会再产生溢出，再返回时判断其是否超过整型的最大值或小于整型的最小值即可

```
long rev = 0;
while (x != 0) {
rev = rev * 10 + x % 10;
	x /= 10;
}
return (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) ? 0 : (int) rev;
```






#####  复杂度分析

- 时间复杂度 `O(log(n))`。在`while`语句中每次将`x`除以10，因此时间复杂度为<a href="https://www.codecogs.com/eqnedit.php?latex=O(log_{10}&space;n)" target="_blank"><img src="https://latex.codecogs.com/gif.latex?O(log_{10}&space;n)" title="O(log_{10} n)" /></a>=`O(logn)`
- 空间复杂度`O(n)`。
