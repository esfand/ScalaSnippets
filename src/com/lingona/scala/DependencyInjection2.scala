package com.lingona.scala


object DependencyInjection2 {
	
	// =======================
	// service interfaces
	trait OnOffDevice {
	  def on: Unit
	  def off: Unit
	}
	trait SensorDevice {
	  def isCoffeePresent: Boolean
	}
	
	// =======================
	// service implementations
	class Heater extends OnOffDevice {
	  def on = println("heater.on")
	  def off = println("heater.off")
	}
	class PotSensor extends SensorDevice {
	  def isCoffeePresent = true
	}
	
	// =======================
	// service declaring two dependencies that it wants injected
	class Warmer(
	  implicit val sensor: SensorDevice, 
	  implicit val onOff: OnOffDevice) {
	
	  def trigger = {
	    if (sensor.isCoffeePresent) onOff.on
	    else onOff.off
	  }
	}
	
	// =======================
	// instantiate the services in a module 
	object Services {
	  implicit val potSensor = new PotSensor
	  implicit val heater = new Heater
	}
	
	
	// =======================
	def main(args : Array[String]) : Unit = {	  
		// =======================
		// import the services into the current scope and the wiring 
		// is done automatically using the implicits
		import Services._
		
		val warmer = new Warmer
		warmer.trigger
	}
}
