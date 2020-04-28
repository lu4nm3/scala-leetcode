package problems

object ProductOfArrayExceptSelf {
  def main(args: Array[String]): Unit = {

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

    println(productExceptSelf(Array(1,2,3)).mkString(", "))
    println(productExceptSelf(Array(1,2,3, 4)).mkString(", "))
  }
}
