## Product of Array Except Self
 
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of 
all the elements of nums except nums[i].
   
### Example

```
Input:  [1,2,3,4]
Output: [24,12,8,6]
```

**Constraint:** It's guaranteed that the product of the elements of any prefix or suffix of the array (including the 
whole array) fits in a 32 bit integer.

**Note:** Please solve it **without division** and in O(n).

**Follow up:**
Could you solve it with constant space complexity? (The output array **does not** count as extra space for the purpose 
of space complexity analysis.)

## Solution

```scala
def productExceptSelf(nums: Array[Int]): Array[Int] = {
  val result = Array.ofDim[Int](nums.length)
  var accumulator = 1

  // iterate through 'nums' from front to back and set 'result[i]' equal to the product of all the elements that
  // came before it by using the accumulator to keep track of the product
  nums.indices.foreach(i => {
    result(i) = accumulator
    accumulator *= nums(i)
  })

  accumulator = 1

  // iterate through 'nums' again but this time from back to front and set 'result[i]' equal to the product of all
  // of the elements that came after it (which we use the accumulator to keep track of) multiplied by the  product
  // of all the elements that came before it (i.e. the value of 'result[i]' itself)
  nums.indices.reverse.foreach(i => {
    result(i) *= accumulator
    accumulator *= nums(i)
  })

  result
}
```

```scala
productExceptSelf(Array(1,2,3))
// res0: Array[Int] = Array(6, 3, 2)
productExceptSelf(Array(1,2,3, 4))
// res1: Array[Int] = Array(24, 12, 8, 6)
```
