package com.lingona.scala

object DependencyInjection7 {
	
	trait OnOffDevice {
		def on:  Unit
		def off: Unit
	}
	
	trait SensorDevice {
		def isCoffeePresent: Boolean
	}
	
	class Heater extends OnOffDevice {
		def on  = println("heater.on")
		def off = println("heater.off")
	}
	
	class PotSensor extends SensorDevice {
		def isCoffeePresent = true
	}
	
	trait Warmer {
		val sensor: SensorDevice
		val onOff: OnOffDevice
		def trigger = {
			if (sensor.isCoffeePresent) onOff.on
			else                        onOff.off
		}
	}
	
	trait WarmerCompanion { self =>
		val sensor: SensorDevice
		val onOff: OnOffDevice
		def apply() = new Warmer { val sensor = self.sensor ; val onOff = self.onOff }
	}
	
	object Env { self =>
		val onOff = new Heater
		val sensor = new PotSensor
		object Warmer extends WarmerCompanion { val sensor = self.sensor ; val onOff = self.onOff }
	}

	
	object Main {
		val warmer = Env.Warmer()
		warmer.trigger
	}	
}
