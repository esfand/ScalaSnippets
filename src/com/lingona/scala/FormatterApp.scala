package com.lingona.scala

import scala.xml.NodeSeq

object FormatterCore {
	
// Abstract Formatter ====================================================================
	
	abstract class Formatter[T, Format] {
		def format(input: T): Format
	}
	
	def as[F] = new {
		def output[T](what: T)(implicit formatter: Formatter[T, F]) = println(formatter.format(what))
	}
	
// String Formatter ======================================================================
	
	implicit object PersonFormatterString extends Formatter[Person, String] {
		def format(input: Person): String = "%s, age %d" format (input.name, input.age)
	}
	
	def ListFormatterString[T](kind: String)(implicit formatter: Formatter[T, String]) = new Formatter[Traversable[T], String] {
		def format(input: Traversable[T]): String = input map formatter.format mkString (kind+" ", ", ", "; ")
	}
	
	implicit object SongFormatterString extends Formatter[Song, String] {
		def format(input: Song): String = input.title+" "+ListFormatterString[Person]("by").format(input.authors)
	}

// XML Formatter ===========================================================================
	
	abstract class FormatterXML[T] extends Formatter[T, NodeSeq] {
		def format(input: T): NodeSeq
	}
	
	def output[T: FormatterXML](what: T) = println(implicitly[FormatterXML[T]].format(what))
	
	def ListFormatterXML[T](kind: String)(implicit formatter: FormatterXML[T]) = new FormatterXML[Traversable[T]] {
		def format(input: Traversable[T]): NodeSeq = 
<List>
    {input map formatter.format}
</List>.copy(label = kind)
	}

	case class Person(name: String, age: Int); 
	object Person {
		implicit object Formatter extends FormatterXML[Person] {
			def format(input: Person): NodeSeq = 
<Person><Name>{input.name}</Name><Age>{input.age}</Age></Person>
		}
	}
	
	case class Song(title: String, authors: Person*); 
	object Song {
		implicit object Formatter extends FormatterXML[Song] {
			def format(input: Song): NodeSeq = 
<Song>
    <Title>{input.title}</Title>
    {ListFormatterXML[Person]("Authors").format(input.authors)}
</Song>
		}
	}
	

}


object FormatterApp {
	import FormatterCore._
		
	def main(args : Array[String]) : Unit = {
		as[NodeSeq].output(Person("John", 32))
		output(Song("War Pigs", Person("Tony Iommi",    62), 
		            			Person("Ozzy Osbourne", 61), 
		            			Person("Geezer Butler", 60),
		            			Person("Bill Ward",     62)))
		as[NodeSeq].output(Song("War Pigs", Person("Tony Iommi",    62), 
	            							Person("Ozzy Osbourne", 61), 
	            							Person("Geezer Butler", 60),
	            							Person("Bill Ward",     62)))
	    as[String].output(Song("War Pigs", Person("Tony Iommi",    62), 
	            						   Person("Ozzy Osbourne", 61), 
	            						   Person("Geezer Butler", 60),
	            						   Person("Bill Ward",     62)))
	}
}

