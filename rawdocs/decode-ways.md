## Decode Ways

A message containing letters from A-Z is being encoded to numbers using the following mapping:

```
'A' -> 1
'B' -> 2
...
'Z' -> 26
```

Given a non-empty string containing only digits, determine the total number of ways to decode it.

### Example 1

```
Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
```

### Example 2

```
Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
```

## Solution

First, we'll use a Map for our number -> letter mapping:
 
```scala mdoc
val mapping = Map(
  "1" -> "A",
  "2" -> "B",
  "3" -> "C",
  "4" -> "D",
  "5" -> "E",
  "6" -> "F",
  "7" -> "G",
  "8" -> "H",
  "9" -> "I",
  "10" -> "J",
  "11" -> "K",
  "12" -> "L",
  "13" -> "M",
  "14" -> "N",
  "15" -> "O",
  "16" -> "P",
  "17" -> "Q",
  "18" -> "R",
  "19" -> "S",
  "20" -> "T",
  "21" -> "U",
  "22" -> "V",
  "23" -> "W",
  "24" -> "X",
  "25" -> "Y",
  "26" -> "Z"
)
```

Then we can create our `decode` function. Here we simply use an outer `decode` function that provides a simpler API than
the inner `decode` function where the bulk of the logic actually resides.

```scala mdoc
def decode(encodedStr: String): Array[String] = {

  def decode(encodedStr: String,
             decodedStr: String,
             result: Array[String]): Array[String] = {
    if (encodedStr.isEmpty) {
      result :+ decodedStr
    } else if (encodedStr.length == 1) {
      decode(
        encodedStr.substring(1),
        decodedStr + mapping(encodedStr.substring(0, 1)),
        result
      )
    } else {
      decode(
        encodedStr.substring(1),
        decodedStr + mapping(encodedStr.substring(0, 1)),
        result
      ) ++
        decode(
          encodedStr.substring(2),
          decodedStr + mapping(encodedStr.substring(0, 2)),
          result
        )
    }
  }

  decode(encodedStr, "", Array.empty)
}
```

```scala mdoc
decode("12")
decode("226")
```

### A more fp approach

Well, sort of. The algorithm we use here is still the same as in the solution above. The only difference is that we make
use of a `StringOps` implicit class in order to extend the `String` type with some helpful methods which will make our
code a bit more concise:

```scala mdoc
def decodeFp(encodedStr: String): Array[String] = {

  implicit class StringOps(str: String) {
    // get the first character of a string
    def one: String = str.substring(0, 1)
    // get the first 2 characters of a string
    def two: String = str.substring(0, 2)
    // get the rest of the string after the first character
    def afterOne: String = str.substring(1)
    // get the rest of the string after the first 2 characters
    def afterTwo: String = str.substring(2)
    // get the resulting value from decoding the string with the mapping
    def decode: String = mapping(str)
  }

  def decode(encoded: String,
             decoded: String,
             result: Array[String]): Array[String] = {
    if (encoded.isEmpty) {
      result :+ decoded
    } else if (encoded.length == 1) {
      decode(encoded.afterOne, decoded + encoded.one.decode, result)
    } else {
      decode(encoded.afterOne, decoded + encoded.one.decode, result) ++
        decode(encoded.afterTwo, decoded + encoded.two.decode, result)
    }
  }

  decode(encodedStr, "", Array.empty)
}
```

```scala mdoc
decodeFp("12")
decodeFp("226")
```
