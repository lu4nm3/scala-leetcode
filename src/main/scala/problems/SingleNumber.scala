package problems

object SingleNumber {
  def main(args: Array[String]): Unit = {
    def findSingleNumberWithMap(nums: List[Int]): Int = {
      var s = Map.empty[Int, Int]

      nums.foreach(i => {
        val currentValue = s.getOrElse(i, 0)
        s += (i -> (currentValue + 1))
      })

      s.filter { case (_, v) => v == 1 }.head._1
    }

    println(findSingleNumberWithMap(List(2,2,1)))
    println(findSingleNumberWithMap(List(4,1,2,1,2)))


    def findSingleNumberWithXor(nums: List[Int]): Int = {
      nums.fold(0)(_ ^ _)
    }

    println(findSingleNumberWithXor(List(2, 2, 1)))
    println(findSingleNumberWithXor(List(4, 1, 2, 1, 2)))
  }
}
