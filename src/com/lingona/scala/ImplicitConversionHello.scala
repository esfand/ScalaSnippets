
package ImplicitConversion {
	
	class IntegerWrapper(val value : Int) { 
		def squared = value * value 
	}
	
	object MyWrappers { 
		implicit def Int2IntegerWrapper(i:Int) = new IntegerWrapper(i) 
	}
}


package com.lingona.scala {
	import ImplicitConversion._
	import MyWrappers._

	object ImplicitConversionHello {
		def main(args : Array[String]) : Unit = {
			println( 4.squared ) // 16
		}
	}
}