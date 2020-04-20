###Problem:

Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

####Example 1:

```
Input: [2,2,1]
Output: 1
```
####Example 2:
```
Input: [4,1,2,1,2]
Output: 4
```
###Solution:

#### Using a hash map

We can use a hash map to keep track of which numbers are duplicates.

```scala mdoc

def findSingleNumberWithMap(nums: List[Int]): Int = {
  var s = Map.empty[Int, Int]

  nums.foreach(i => {
    val currentValue = s.getOrElse(i, 0)
    s += (i -> (currentValue + 1))
  })

  s.filter { case (_, v) => v == 1 }.head._1
}
```

```scala mdoc
findSingleNumberWithMap(List(2,2,1))
findSingleNumberWithMap(List(4,1,2,1,2))
```

#### Using bit operations

We can use the XOR bit operation. In this case, there are 2 rules to remember:

- A number xor'ed by itself is `0`
- A number xor'ed by `0` is itself 

```scala mdoc
def findSingleNumberWithXor(nums: List[Int]): Int = {
    nums.fold(0)(_ ^ _)
}
```

```scala mdoc
findSingleNumberWithXor(List(2,2,1))
findSingleNumberWithXor(List(4,1,2,1,2))
```
