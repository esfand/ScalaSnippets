
package RingCore {
	
	trait Ring[T] {
		def one:  T
		def zero: T
		def plus(a: T, b: T) : T
	}
	
	object IntRing extends Ring[Int] {
		override def one  = 1
		override def zero = 0
		override def plus(a: Int, b: Int) = a+b
		
		def main(args: Array[String]) {
		}
	}
}

package com.lingona.scala {
	import RingCore._
	import IntRing._

	object RingApp {
		def main(args : Array[String]) : Unit = {
			println(one)
			println(zero)
			println(plus(2,3))			
		}
	}
}