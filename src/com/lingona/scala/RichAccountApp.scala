package com.lingona.scala

//import scala.collection.JavaConversions._
import scala.swing.BufferWrapper


class RichAccount(value: Account) {
/*
  //..
  def names =
    (new BufferWrapper[String] {
    	def underlying = value.getNames()
    }).toList

  def addresses =
    (new BufferWrapper[Address] {
      def underlying = value.getAddresses()
    }).toList

  def interests =
    (new BufferWrapper[java.math.BigDecimal] {
      def underlying = value.getInterests()
    }).toList

    // check if the account belongs to a particular name
	def belongsTo(name: String): Boolean = {
	    names exists (s => s == name)
	}

  def << (address: Address) = {
    value.addAddress(address)
    this
  }

  def << (name: String) = {
    value.addName(name)
    this
  }
  
  def calculateInterest(precision: java.math.BigDecimal)
           (implicit calc: Calculator): java.math.BigDecimal = {
    value.calculate(precision, calc)
    value.getInterest()
  }

  def withAccount(accountType: AccountType)(operation: => Unit) = {
    if (!value.isOpen())
      throw new Exception("account not open")

    // other validations

    if (value.getAccountType().equals(accountType))
      operation
    }
  }
*/  
  
}



object RichAccountApp {
	
	implicit def enrichAccount(acc: Account): RichAccount = new RichAccount(acc)

/*	
	implicit val calc = new DefaultCalculator
	
	def main(args : Array[String]) : Unit = {
		
		// instantiate using Java class
		val myAccount = new Account("debasish", "100", new Address(12, "street_1", "700097"))
		
		// use the functional API of Scala on this object
		myAccount.names.reverse foreach(println)
		
		// filter out all accounts belonging to debasish
		accounts filter(_ belongsTo "debasish") foreach(a => println(a.getName()))
		
		accounts.filter(_ belongsTo "debasish")
		        .map(_.calculateInterest(java.math.BigDecimal.valueOf(0.25)))
		        .filter(_ != 0)
		        .foldLeft(java.math.BigDecimal.ZERO)(_.add(_))

		myAccount << "shubhasis"
		    	  << new Address(13, "street_1n", "700098")
		    	  << "ashis"
		        
		val accounts = List[Account](..)
		accounts filter(_ belongsTo "debasish")
		    foreach(a => println(a.calculateInterest(java.math.BigDecimal.valueOf(0.25))))
	

		a1.withAccount(AccountType.SAVINGS) {
			println(a1.calculateInterest(java.math.BigDecimal.valueOf(0.25)))
		}
	}
*/
}
