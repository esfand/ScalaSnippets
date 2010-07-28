package com.lingona.scala

object AcceptableApp {

	abstract class Acceptable[T]
	
	object Acceptable {
		implicit object IntOk  extends Acceptable[Int]
		implicit object LongOk extends Acceptable[Long]
	}

	def f[T: Acceptable](t: T) = t	
	
	def main(args : Array[String]) : Unit = {
		f(1)
		f(1L)
//		f(1.0) // Not acceptable
	}
}
