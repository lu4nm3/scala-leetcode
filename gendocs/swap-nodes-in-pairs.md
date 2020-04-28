## Swap Nodes in Pairs

Given a linked list, swap every two adjacent nodes and return its head.

**Note:** You may not modify the values in the list's nodes, only nodes itself may be changed.

### Example:

```
Given 1->2->3->4, you should return the list as 2->1->4->3.
```

## Solution:

First let's define what our list node data structure will look like:

```scala
case class Node(x: Int, next: Option[Node] = None)

object Node {
  import scala.language.implicitConversions

  implicit def ListNodeToSome(listNode: Node): Option[Node] = {
    Some(listNode)
  }
}
```

Notice that we defined an implicit conversion to wrap a `ListNode` within `Some`. This will come in handy to make our 
code a bit more concise by avoiding having to do a lot of manual wrapping.

```scala
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
```

```scala
swap(Node(1))
// res0: Node = Node(1, None)
swap(Node(1, Node(2)))
// res1: Node = Node(2, Some(Node(1, None)))
swap(Node(1, Node(2, Node(3))))
// res2: Node = Node(2, Some(Node(1, Some(Node(3, None)))))
swap(Node(1, Node(2, Node(3, Node(4)))))
// res3: Node = Node(2, Some(Node(1, Some(Node(4, Some(Node(3, None)))))))
```
