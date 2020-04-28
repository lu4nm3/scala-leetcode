## Two Sum

Given an array of integers, return indices of the two numbers such that they add up to a specific target.

**Note:** You may assume that each input would have exactly one solution, and you may not use the same element twice.

### Example

```
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
```

## Solution

```scala
def twoSum(nums: Array[Int], target: Int): Array[Int] = {
  val numMap = nums.indices.map(i => (nums(i), i)).toMap

  val i1 = nums.indices.find(i => numMap.contains(target - nums(i))).get
  val i2 = numMap(target - nums(i1))

  Array(i1, i2)
}
```

```scala
twoSum(Array(2, 7, 11, 15), 9)
// res0: Array[Int] = Array(0, 1)
```
