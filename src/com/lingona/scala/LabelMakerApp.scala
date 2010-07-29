
package LabelMakerCore {
	
	case class Address(no:     Int, 
					   street: String, 
					   city:   String, 
					   state:  String, 
					   zip:    String)
	
	
	trait LabelMaker[T] {
	  def toLabel(value: T): String
	}
	
	// Module 1 (A Type Class) -- The default module 
	object LabelMaker {
		implicit object AddressLabelMaker extends LabelMaker[Address] {
			def toLabel(address: Address): String = {
				import address._
				"%d %s, %s, %s - %s".format(no, street, city, state, zip)
			}
		}
	}
	
	// Module 2 (Another Type Class)
	object SpecialLabelMaker {
		implicit object AddressLabelMaker extends LabelMaker[Address] {
			def toLabel(address: Address): String = {
				import address._
				"[%d %s, %s, %s - %s]".format(no, street, city, state, zip)
			}
		}
	}
	
	
	// adapter class
	case class AddressLabelMaker extends LabelMaker[Address] {
		def toLabel(address: Address) = {
			import address._
			"%d %s, %s, %s - %s".format(no, street, city, state, zip)
		}
	}
}

package com.lingona.scala {
	import LabelMakerCore._

	object LabelMakerApp {
		def main(args : Array[String]) : Unit = {
			
			// the adapter provides the interface of the LabelMaker on an Address
			val label1 = AddressLabelMaker().toLabel(Address(1, "Rihlakuja", "Helsinki", "FI", "00920"))
			println(label1)
			
			def printLabel[T](t: T)(implicit lm: LabelMaker[T]) = lm.toLabel(t)
			import SpecialLabelMaker._
			val label  = printLabel(Address(100, "Monroe Street", "Denver", "CO", "80231"))		
			println(label)
			
			// Explicit Parameter
			val label2 = printLabel(Address(100, "Monroe Street", "Denver", "CO", "80231"))(LabelMaker.AddressLabelMaker)
			println(label2)	
		}
	}
}
