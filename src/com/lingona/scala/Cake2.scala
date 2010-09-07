package com.lingona.scala

// Is there a convenient way to simulate static member inheritance in Scala? 

object Cake2 {

// Given a class X which we want to have a 'static' member m, make X an inner class 
// of a new class Y and make m a member of Y. 
// Any instance of Y (or if you were to make Y an object) now emulates X having a static member.
// We can then subclass Y, override m, and subclass X. 	
	
   class Y {
      def m = "m"
    	  
      class X {
        def x = "x"
      }
    }

	class Z1 extends Y {
		override def m = "m2"
			
		class X2 extends X {
        override def x = "x2"
      }
    }

// Typically this means that much of your code (at least that which accesses m) will 
// need to go within Y (or Z).  This is best done by mixing in traits, e.g.:

	trait W  {
    	self: Y => 
		def q = "q" + m
    }

// and then we mix it in:

    class Z2 extends Y
             with    W
    {
    	//...
    }

	
	def main(args : Array[String]) : Unit = {
		println( (new Z2).q )
	}
}
