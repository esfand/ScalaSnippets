package com.lingona.scala

object Main extends Application {

	object WeekDay extends Enumeration {
		type WeekDay = Value
		val Mon = Value("Mon")
		val Tue = Value("Tue") 
		val Wed = Value("Wed")
		val Thu = Value("Thu") 
		val Fri = Value("Fri") 
		val Sat = Value("Sat") 
		val Sun = Value("Sun") 
	} 
	import WeekDay._
	def isWorkingDay(d: WeekDay) = ! (d == Sat || d == Sun)
	WeekDay.iterator filter isWorkingDay foreach println
}

object WeekDay extends Enumeration("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat") {
  type WeekDay = Value
  val  Sun, Mon, Tue, Wed, Thu, Fri, Sat = Value
}

//WeekDay.valueOf("Wed") // returns Some(Wed)
//WeekDay.Fri.toString   // returns Fri
