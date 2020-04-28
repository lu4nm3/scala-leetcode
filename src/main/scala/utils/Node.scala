package utils


case class Node(x: Int, next: Option[Node] = None)

object Node {
  import scala.language.implicitConversions

  implicit def ListNodeToSome(listNode: Node): Option[Node] = {
    Some(listNode)
  }
}
