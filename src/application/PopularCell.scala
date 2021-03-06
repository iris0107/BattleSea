package application

import java.util.Map;
import java.util.ArrayList;
import scala.collection.immutable.List

class PopularCell {

  def getCell(coordinates: Array[Int], keysForCoordinates: Array[Int], amount: Int): Int =
    {
      // create an empty map
      var states = scala.collection.mutable.Map[Int, Int]()

      // Create map with value
      for (i <- (0).to(amount - 1)) {
        states += (coordinates(i) -> keysForCoordinates(i))
      }
      // println("Keys : " + states.keys)  // for check in console 
      //println("Values  : " + states.values)

      var cell = 0
      states.toList sortBy (_._2) foreach {
        case (key, value) =>
          println(key + " = " + value)
          cell = key
      }
      println("Keys : " + cell)
      cell
    }

  def getField(fieldFilling: Array[Int]): Int =
    {
      val field = for (e <- fieldFilling) yield e
      println("Check = " + field.max + " " + field.size)
      field.max
    }

  def winsFirstPlayer(winner: Array[Int]): Int =
    {
      val first = for (e <- winner if e == 1) yield e
      println("Check = " + first.size)
      first.size
    }

  def winsSecondPlayer(winner: Array[Int]): Int =
    {
      val second = for (e <- winner if e == 2) yield e
      println("Check = " + second.size)
      second.size
    }

  def findMaxSeq(fieldFilling: Array[Array[Array[Int]]], findSeq: Array[Int], replays: Int): Array[Int] = findSeq match {
    case _ => {
      var sum = 0
      var amount = 0

      for (i <- (0).to(replays - 1)) {
        var find = i
        for (l <- (0).to(replays - 1)) {
          sum = 0
          for (k <- (20).to(39)) {
            for (j <- (0).to(9)) {

              if (fieldFilling(find)(k)(j) == fieldFilling(l)(k)(j)) {
                sum = sum + 1
              }
              if (sum == 200) {
                amount = amount + 1
              }
            }
          }
        }
        findSeq(i) = amount
        amount = 0
      }
      var findMaxRepeat = 0
      var currentPosition = 0
      for (i <- (0).to(replays - 1)) {
        if (findSeq(i) > findMaxRepeat) {
          val change = findSeq(i)
          findSeq(i) = findMaxRepeat
          findMaxRepeat = change
          currentPosition = i
        }
      }

      var result: Array[Int] = new Array[Int](3)
      result(0) = findMaxRepeat
      result(1) = currentPosition
      result
    }
  }
}