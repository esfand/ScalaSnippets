
package Scotch28Core {
      sealed abstract            class   Preparation
      case object Neat           extends Preparation
      case object OnTheRocks     extends Preparation
      case object WithExtraWater extends Preparation

      sealed abstract   class   Glass
      case object Short extends Glass
      case object Tall  extends Glass
      case object Tulip extends Glass

      case class OrderOfScotch(val brand:  String , 
    		  				   val mode:   Preparation = Neat, 
    		  				   val double: Boolean = false, 
    		  				   val glass:  Option[Glass] = None)
      {
        val invalid: Boolean = double && glass.isDefined && glass.get == Short
        if(invalid) {
            throw new IllegalArgumentException("Illegal combination")
        }
        def prepare(p:Preparation) = copy(mode = p)
        def isDouble(d:Boolean) = copy(double = d)
        def inGlass(g:Glass) = copy(glass = Option(g))
      }
      
      object OrderBuilder {
        def scotch(brand:String) = OrderOfScotch(brand)
      }
}

/*
class ScotchBuilderSpec extends Spec with ShouldMatchers {
  import OrderBuilder._
  describe("given two scotches with the same configuration") {
	val constructorResult = OrderOfScotch("Glenfoobar", WithExtraWater, false, Option(Tulip))
	val builderResult = scotch("Glenfoobar") prepare WithExtraWater inGlass Tulip
	describe("when created by constructor and by the builder") {
	  it("they should return identical results") {
		constructorResult should be (builderResult)
		println("success!")
	  }
	}
  }
  describe("trying to create an illegal order") {
	describe("like combining a double portion with a short glass") {
	  it("should throw an exception, for now") {
		evaluating { OrderOfScotch("Glenfoobar", Neat, true, Option(Short)) } should produce[Throwable]
		evaluating { scotch("Glenfoobar") isDouble(true) inGlass Short } should produce[Throwable]
	  }
	}
  }
}
*/

package com.lingona.scala {
	import Scotch28Core._
	import Scotch28Core.OrderBuilder._

	object Scotch28 {
	  def main(args : Array[String]) : Unit = {
		val scotchOrder1 = scotch("Glenfoobar") prepare WithExtraWater inGlass Tulip
		val scotchOrder2 = scotch("Talisker")   inGlass Tall prepare Neat	 	  
	  }
	}
}
