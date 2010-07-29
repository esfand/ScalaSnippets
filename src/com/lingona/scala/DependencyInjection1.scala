package com.lingona.scala


object DependencyInjection1 {
	
	// =======================
	// service interfaces
	trait OnOffDevice {
	  def on:  Unit
	  def off: Unit
	}
	trait SensorDevice {
	  def isCoffeePresent: Boolean
	}
	
	// =======================
	// service implementations
	class Heater extends OnOffDevice {
	  def on  = println("heater.on")
	  def off = println("heater.off")
	}
	class PotSensor extends SensorDevice {
	  def isCoffeePresent = true
	}
	
	// =======================
	// service declaring two dependencies that it wants injected,
	// is using structural typing to declare its dependencies
	class Warmer(env: {
	  val potSensor: SensorDevice
	  val heater:    OnOffDevice
	}) {
	  def trigger = {
	    if (env.potSensor.isCoffeePresent) env.heater.on
	    else env.heater.off
	  }
	}
	
	class Client(env : { val warmer: Warmer }) {
	  env.warmer.trigger
	}
	
	// =======================
	// instantiate the services in a configuration module 
	object Config {
	  lazy val potSensor = new PotSensor
	  lazy val heater = new Heater
	  lazy val warmer = new Warmer(this) // this is where injection happens
	}

	
	// =======================
	def main(args : Array[String]) : Unit = {	  
		new Client(Config)
	}
}
