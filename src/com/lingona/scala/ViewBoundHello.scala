package com.lingona.scala

object ViewBoundHello {
	
//	implicit def list2ordered[A](x: List[A])(implicit elem2ordered: a => Ordered[A]): Ordered[List[A]] = 
//		new Ordered[List[A]] { /* .. */ }
    
	implicit def list2ordered[A <% Ordered[A]](x: List[A]): Ordered[List[A]] = 
		{ null/*...*/ }

//    implicit def int2ordered(x: Int): Ordered[Int] = 
//    	new Ordered[Int] { /* .. */ }
		
	def main(args : Array[String]) : Unit = {
		
	}
}
