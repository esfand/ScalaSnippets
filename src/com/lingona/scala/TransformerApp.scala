package com.lingona.scala


object TransformerApp {

	def countToThree(numberToString: Int => String) =
		for (n <- List.range(1,4)) 
			println(numberToString(n))

	def numberToGermanString(n: Int) = n match {
		case 0 => "null"
		case 1 => "ein"
		case 2 => "zwei"
		case 3 => "drei"
		case _ => throw new RuntimeException("Ich bin nicht ein Berliner")
	}
	
	object NumberToSpanishStringTransformer extends Function1[Int, String] {
		def apply(n: Int) = n match {
		    case 0 => "cero"
		    case 1 => "uno"
		    case 2 => "dos"
		    case 3 => "tres"
		    case _ => throw new RuntimeException("Soy un sustantivo")
		}
	}
			
	val words = Array("none", "one", "a couple", "many")
  
	def main(args : Array[String]) : Unit = {
		
		countToThree(words)			
		countToThree(numberToGermanString)		
		countToThree(NumberToSpanishStringTransformer)			
	}
}
