## Best Time to Buy and Sell Stock

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

**Note:** You cannot sell a stock before you buy one.

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

```scala mdoc
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

```scala mdoc
maxProfit(Array(7, 1, 5, 3, 6, 4))

maxProfit(Array(7, 6, 4, 3, 1))

maxProfit(Array(7, 1, 5, 8, 3, 0, 6, 4, 7))

maxProfit(Array(7, 1, 5, 8, 3, 0, 6, 4, 7, 9))
```

### A more fp approach

We can take a more functional programming approach by folding the array of prices over the state that we need to keep
track of. Whereas before we used 5 separate variables to do this, we can group these into a `Tracker` data type which 
makes it easier when folding.

```scala mdoc
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

```scala mdoc
maxProfitFp(Array(7, 1, 5, 3, 6, 4))

maxProfitFp(Array(7, 6, 4, 3, 1))

maxProfitFp(Array(7, 1, 5, 8, 3, 0, 6, 4, 7))

maxProfitFp(Array(7, 1, 5, 8, 3, 0, 6, 4, 7, 9))
```
