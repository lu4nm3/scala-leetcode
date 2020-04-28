package problems

import utils.Node

object SwapNodesInPairs {
  def main(args: Array[String]): Unit = {

    def swap(head: Node): Node = {

      def innerSwap(head: Option[Node]): Option[Node] = {
        if (head.isEmpty || head.get.next.isEmpty) {
          head
        } else {
          val currentHead = head.get
          val headNext = currentHead.next.get
          headNext.copy(
            next = currentHead.copy(next = innerSwap(headNext.next))
          )
        }
      }

      innerSwap(head).get
    }

    println(swap(Node(1)))
    println(swap(Node(1, Node(2))))
    println(swap(Node(1, Node(2, Node(3)))))
    println(swap(Node(1, Node(2, Node(3, Node(4))))))
  }
}
