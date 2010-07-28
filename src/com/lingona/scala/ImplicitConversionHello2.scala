package com.lingona.scala {
	
    final class RichChar(c: Char) {
      def isDigit: Boolean = Character.isDigit(c)
      // isLetter, isWhitespace, etc.
    }
    
    object RichCharTest {
      implicit def charWrapper(c: Char) = new RichChar(c)
      def main(args: Array[String]) {
    	  
        println( '0'.isDigit )
        
      }
    }
}
