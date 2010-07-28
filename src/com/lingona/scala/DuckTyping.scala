
package ducktyping {
	class Duck {
	  def quack    = println("Quaaaaaack !")
	  def feathers = println("The duck has white and gray feathers.")
	}
	 
	class Person {
	  def quack    = println("The person imitates a duck.")
	  def feathers = println("The person takes a feather from the ground and shows it.")
	}
	 
	object Forest {
		// Accepts any object that has a quack and feathers method,
		def inTheForest(duck: { def quack; def feathers }) = {
		  duck.quack
		  duck.feathers
		}
	}
	
	object UsefulTypes {
	  type Duck = { def quack; def feathers }
	}	
}

package com.lingona.scala {
	import ducktyping._
	import ducktyping.Forest._

	object DuckTyping {
		def main(args : Array[String]) : Unit = {
			inTheForest(new Person)
			inTheForest(new Duck)		
		}
	}
}