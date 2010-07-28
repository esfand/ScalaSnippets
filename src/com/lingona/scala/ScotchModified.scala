package com.lingona.scala

object BuilderPattern {
	
  sealed abstract        class   Preparation // This is one way of coding enum-like things in scala
  case object Neat       extends Preparation
  case object OnTheRocks extends Preparation
  case object WithWater  extends Preparation

  sealed abstract   class   Glass
  case object Short extends Glass
  case object Tall  extends Glass
  case object Tulip extends Glass

  case class OrderOfScotch private[BuilderPattern] (val brand:    String, 
		  											val mode:     Preparation, 
		  											val isDouble: Boolean, 
		  											val glass:    Option[Glass])

  abstract class TRUE  
  abstract class FALSE

  abstract class ScotchBuilder { self:ScotchBuilder =>
    protected[BuilderPattern] val theBrand:Option[String]
    protected[BuilderPattern] val theMode:Option[Preparation]
    protected[BuilderPattern] val theDoubleStatus:Option[Boolean]
    protected[BuilderPattern] val theGlass:Option[Glass]

    type HAS_BRAND
    type HAS_MODE
    type HAS_DOUBLE_STATUS

    def withBrand(b:String) = new ScotchBuilder {
      protected[BuilderPattern] val theBrand:Option[String] = Some(b)
      protected[BuilderPattern] val theMode:Option[Preparation] = self.theMode
      protected[BuilderPattern] val theDoubleStatus:Option[Boolean] = self.theDoubleStatus
      protected[BuilderPattern] val theGlass:Option[Glass] = self.theGlass

      type HAS_BRAND = TRUE
      type HAS_MODE = self.HAS_MODE
      type HAS_DOUBLE_STATUS = self.HAS_DOUBLE_STATUS
    }

    def withMode(p:Preparation) = new ScotchBuilder {
      protected[BuilderPattern] val theBrand:Option[String] = self.theBrand
      protected[BuilderPattern] val theMode:Option[Preparation] = Some(p)
      protected[BuilderPattern] val theDoubleStatus:Option[Boolean] = self.theDoubleStatus
      protected[BuilderPattern] val theGlass:Option[Glass] = self.theGlass

      type HAS_BRAND = self.HAS_BRAND
      type HAS_MODE = TRUE
      type HAS_DOUBLE_STATUS = self.HAS_DOUBLE_STATUS
    }


    def isDouble(b:Boolean) = new ScotchBuilder {
      protected[BuilderPattern] val theBrand:Option[String] = self.theBrand
      protected[BuilderPattern] val theMode:Option[Preparation] = self.theMode
      protected[BuilderPattern] val theDoubleStatus:Option[Boolean] = Some(b)
      protected[BuilderPattern] val theGlass:Option[Glass] = self.theGlass

      type HAS_BRAND = self.HAS_BRAND
      type HAS_MODE = self.HAS_MODE
      type HAS_DOUBLE_STATUS = TRUE
    }
     
    def withGlass(g:Glass) = new ScotchBuilder {
      protected[BuilderPattern] val theBrand:Option[String] = self.theBrand
      protected[BuilderPattern] val theMode:Option[Preparation] = self.theMode
      protected[BuilderPattern] val theDoubleStatus:Option[Boolean] = self.theDoubleStatus
      protected[BuilderPattern] val theGlass:Option[Glass] = Some(g)

      type HAS_BRAND = self.HAS_BRAND
      type HAS_MODE = self.HAS_MODE
      type HAS_DOUBLE_STATUS = self.HAS_DOUBLE_STATUS
    }

  }

  type CompleteBuilder = ScotchBuilder {
    type HAS_BRAND = TRUE
    type HAS_MODE = TRUE
    type HAS_DOUBLE_STATUS = TRUE
  }

  implicit def enableBuild(builder:CompleteBuilder) = new {
    def build() = 
      new OrderOfScotch(builder.theBrand.get, 
    		  			builder.theMode.get, 
    		  			builder.theDoubleStatus.get, 
    		  			builder.theGlass);
  }

  def builder = new ScotchBuilder {
    protected[BuilderPattern] val theBrand:Option[String] = None
    protected[BuilderPattern] val theMode:Option[Preparation] = None
    protected[BuilderPattern] val theDoubleStatus:Option[Boolean] = None
    protected[BuilderPattern] val theGlass:Option[Glass] = None

    type HAS_BRAND = FALSE
    type HAS_MODE = FALSE
    type HAS_DOUBLE_STATUS = FALSE
  }
}



object ScotchModified {
  def main(args : Array[String]) : Unit = {}
}
