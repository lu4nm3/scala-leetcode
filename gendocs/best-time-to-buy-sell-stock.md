## Best Time to Buy and Sell Stock

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

### Example 1:

```
Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
```
 
### Example 2:

```
Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
```

## Solution

```scala
def maxProfit(prices: Array[Int]): Int = {
  var buyOnIndex = -1
  var sellOnIndex = -1

  var minIndex = -1
  var minPrice = Integer.MAX_VALUE

  var maxDifference = 0

  prices.indices.foreach(i => {
    val currentPrice = prices(i)

    if (currentPrice < minPrice) {
      minPrice = currentPrice
      minIndex = i
    }

    if (currentPrice - minPrice > maxDifference) {
      maxDifference = currentPrice - minPrice
      buyOnIndex = minIndex
      sellOnIndex = i
    }
  })

  if (maxDifference > 0) {
    println(
      s"Buy on day ${buyOnIndex + 1} (price = ${prices(buyOnIndex)}) and sell on day ${sellOnIndex + 1} (price = ${prices(
        sellOnIndex
      )}), profit =  ${prices(sellOnIndex)} - ${prices(buyOnIndex)} = ${maxDifference}"
    )
  } else {
    println("No transaction is done. Profit is 0")
  }

  maxDifference
}
```

```scala
maxProfit(Array(7, 1, 5, 3, 6, 4))
// Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit =  6 - 1 = 5
// res0: Int = 5

maxProfit(Array(7, 6, 4, 3, 1))
// No transaction is done. Profit is 0
// res1: Int = 0

maxProfit(Array(7, 1, 5, 8, 3, 0, 6, 4, 7))
// Buy on day 2 (price = 1) and sell on day 4 (price = 8), profit =  8 - 1 = 7
// res2: Int = 7

maxProfit(Array(7, 1, 5, 8, 3, 0, 6, 4, 7, 9))
// Buy on day 6 (price = 0) and sell on day 10 (price = 9), profit =  9 - 0 = 9
// res3: Int = 9
```

### A more fp approach

```scala
case class Tracker(buyOnIndex: Int = -1,
                   sellOnIndex: Int = -1,
                   minIndex: Int = -1,
                   minPrice: Int = Integer.MAX_VALUE,
                   maxDifference: Int = 0)

def maxProfitFp(prices: Array[Int]): Int = {
  val t = prices.indices.foldLeft(Tracker())((t, i) => {
    var newT = t

    val currentPrice = prices(i)

    if (currentPrice < t.minPrice) {
      newT = newT.copy(minPrice = currentPrice)
      newT = newT.copy(minIndex = i)
    }

    if (currentPrice - newT.minPrice > newT.maxDifference) {
      newT = newT.copy(maxDifference = currentPrice - newT.minPrice)
      newT = newT.copy(buyOnIndex = newT.minIndex)
      newT = newT.copy(sellOnIndex = i)
    }
    newT
  })

  if (t.maxDifference > 0) {
    println(
      s"Buy on day ${t.buyOnIndex + 1} (price = ${prices(t.buyOnIndex)}) and sell on day ${t.sellOnIndex + 1} (price = ${prices(
        t.sellOnIndex
      )}), profit =  ${prices(t.sellOnIndex)} - ${prices(t.buyOnIndex)} = ${t.maxDifference}"
    )
  } else {
    println("No transaction is done. Profit is 0")
  }

  t.maxDifference
}
```

```scala
maxProfitFp(Array(7, 1, 5, 3, 6, 4))
// Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit =  6 - 1 = 5
// res4: Int = 5

maxProfitFp(Array(7, 6, 4, 3, 1))
// No transaction is done. Profit is 0
// res5: Int = 0

maxProfitFp(Array(7, 1, 5, 8, 3, 0, 6, 4, 7))
// Buy on day 2 (price = 1) and sell on day 4 (price = 8), profit =  8 - 1 = 7
// res6: Int = 7

maxProfitFp(Array(7, 1, 5, 8, 3, 0, 6, 4, 7, 9))
// Buy on day 6 (price = 0) and sell on day 10 (price = 9), profit =  9 - 0 = 9
// res7: Int = 9
```