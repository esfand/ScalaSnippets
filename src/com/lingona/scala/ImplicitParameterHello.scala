package com.lingona.scala

object ImplicitParameterHello {
	def main(args : Array[String]) : Unit = {
		
		abstract class X[T] { def id : Unit }
		
		implicit def a[T] = new X[T]   { def id = println("generic") }
		implicit def b    = new X[Int] { def id = println("Int") }
		
		def f[T: X](a : T) = implicitly[X[T]].id  // def f[T](a : T)(implicit g: X[T]) = g.id
		
		// A more specific implicit has preference over a more generic one.
		f(5)     // f(5)(b)    Int
		f('c')   // f('c')(a)  generic
	}
}
