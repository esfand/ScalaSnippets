// code-examples/AppDesign/abstractions/encoded-string.scala
package encodedstring {

  trait EncodedString {
    protected[encodedstring] val string: String
    val separator: EncodedString.Separator.Delimiter

    override def toString = string

    def toTokens = string.split(separator.toString).toList
  }

  object EncodedString {
    object Separator extends Enumeration {
      type Delimiter = Value
      val  COMMA     = Value(",")
      val  TAB       = Value("\t")
    }

    def apply(s: String, sep: Separator.Delimiter) = sep match {
      case Separator.COMMA => impl.CSV(s)
      case Separator.TAB   => impl.TSV(s)
    }

    def unapply(es: EncodedString) = Some(Pair(es.string, es.separator))
  }

  package impl {
    private[encodedstring] case class CSV(override val string: String) extends EncodedString {
      override val separator = EncodedString.Separator.COMMA
    }

    private[encodedstring] case class TSV(override val string: String) extends EncodedString {
      override val separator = EncodedString.Separator.TAB
    }
  }
}


// code-examples/AppDesign/abstractions/encoded-string-script.scala
package com.lingona.scala {
	object Components extends Application {	
		import encodedstring._
		import encodedstring.EncodedString._
		
		def p(s: EncodedString) = {
			println("EncodedString: " + s)
			s.toTokens foreach (x => println("token: " + x))
		}
		
		val csv = EncodedString("Scala,is,great!",   Separator.COMMA)
		val tsv = EncodedString("Scala\tis\tgreat!", Separator.TAB)
		
		p(csv)
		p(tsv)
		
		println( "\nExtraction:" )
		List(csv, "ProgrammingScala", tsv, 3.14159) foreach {
		  case EncodedString(str, delim) => println( "EncodedString: \"" + str + "\", delimiter: \"" + delim + "\"" )
		  case s: String                 => println( "String: " + s )
		  case x                         => println( "Unknown Value: " + x )
		}
	}
}