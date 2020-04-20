package utils

case class BinaryTreeNode[T](value: T, left: Option[BinaryTreeNode[T]] = None, right: Option[BinaryTreeNode[T]] = None)
