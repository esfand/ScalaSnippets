package com.lingona.scala

object RichArrayApp {
	
	class RichArray[T](value: Array[T]) {	
		def append[T : Manifest](other: Array[T]): Array[T] = {
			val result = new Array[T](value.length + other.length)
			Array.copy(value, 0, result, 0, value.length)
			Array.copy(other, 0, result, value.length, other.length)
			result
		}
	}

	implicit def enrichArray[T: Manifest](xs: Array[T]) = new RichArray[T](xs)
	
	def main(args : Array[String]) : Unit = {
		val x = Array(1, 2, 3)
		val y = Array(4, 5, 6)
		val z = x append y
	}
}
