package problems

object TwoSum {
  def main(args: Array[String]): Unit = {

    def twoSum(nums: Array[Int], target: Int): Array[Int] = {
      val numMap = nums.indices.map(i => (nums(i), i)).toMap

      val i1 = nums.indices.find(i => numMap.contains(target - nums(i))).get
      val i2 = numMap(target - nums(i1))

      Array(i1, i2)
    }

    println(twoSum(Array(2, 7, 11, 15), 9).mkString(", "))
  }
}
