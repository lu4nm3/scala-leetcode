package problems

object BestTimeToBuySellStock {
  def main(args: Array[String]): Unit = {

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

    println(maxProfit(Array(7, 1, 5, 3, 6, 4)))
    println(maxProfit(Array(7, 6, 4, 3, 1)))

    println(maxProfit(Array(7, 1, 5, 8, 3, 0, 6, 4, 7)))
    println(maxProfit(Array(7, 1, 5, 8, 3, 0, 6, 4, 7, 9)))

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

    println(maxProfitFp(Array(7, 1, 5, 3, 6, 4)))
    println(maxProfitFp(Array(7, 6, 4, 3, 1)))

    println(maxProfitFp(Array(7, 1, 5, 8, 3, 0, 6, 4, 7)))
    println(maxProfitFp(Array(7, 1, 5, 8, 3, 0, 6, 4, 7, 9)))
  }
}
