package com.lingona.scala

	object DependencyInjection6 {
	// Simplified version of Cake pattern by Jesper de Jong
	// see: http://jonasboner.com/2008/10/06/real-world-scala-dependency-injection-di.html
	
	// ========================================
	// Service interfaces
	
	// OnOffDevice component and interface
	trait OnOffDeviceComponent {
	    val onOff: OnOffDevice
	
	    trait OnOffDevice {
	        def on: Unit
	        def off: Unit
	    }
	}
	
	// SensorDevice component and interface
	trait SensorDeviceComponent {
	    val sensor: SensorDevice
	
	    trait SensorDevice {
	        def isCoffeePresent: Boolean
	    }
	}
	
	// Warmer component and interface
	trait WarmerComponent {
	    val warmer: Warmer
	
	    trait Warmer {
	        def trigger: Unit
	    }
	}
	
	// ========================================
	// Service implementations
	
	// Note: Instead of OnOffDeviceComponentImpl with a Heater class inside it, I create a HeaterComponent,
	// and I don't need the Heater class. The same for the other components. That way, the ComponentRegistry
	// doesn't need to deal with the classes in the component implementation (Heater, PotSensor and Warmer)
	// of the original version.
	
	// Heater, an implementation of an OnOffDevice
	trait HeaterComponent extends OnOffDeviceComponent {
	    val onOff = new OnOffDevice {
	        def on = println("heater.on")
	        def off = println("heater.off")
	    }
	}
	
	// PotSensor, an implementation of a SensorDevice
	trait PotSensorComponent extends SensorDeviceComponent {
	    val sensor = new SensorDevice {
	        def isCoffeePresent = true
	    }
	}
	
	// WarmerComponentImpl, an implementation of a Warmer
	// Depends on SensorDeviceComponent and OnOffDeviceComponent using self type annotation
	trait WarmerComponentImpl extends WarmerComponent {
	    this: SensorDeviceComponent with OnOffDeviceComponent =>
	
	    val warmer = new Warmer {
	        def trigger = {
	            if (sensor.isCoffeePresent) onOff.on
	            else onOff.off
	        }
	    }
	}
	
	// ========================================
	
	// Component registry to bind components together
	object ComponentRegistry extends WarmerComponentImpl 
							 with    PotSensorComponent 
							 with    HeaterComponent
	
	object Main {
	    def main(args: Array[String]): Unit = {
	    	
	        val warmer = ComponentRegistry.warmer
	        warmer.trigger
	
	        // You could also do it without the ComponentRegistry
	        val warmer2 = (new WarmerComponentImpl with PotSensorComponent with HeaterComponent).warmer
	        warmer2.trigger
	    }
	}	
}
