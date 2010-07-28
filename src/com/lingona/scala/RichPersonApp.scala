package com.lingona.scala


// Core ----------------------------------------------------------------------
package org.dg.biz.core {
	class Person(val lastName: String,val firstName: String,val age: Int) {
	    //..
	    //.. details
	
	    override def toString(): String = {
	        lastName + " " + firstName + " " + age
	    }
	}
}

// UI Framework ==============================================================
package org.dg.biz.ui {
	import org.dg.biz.core._
	import javax.swing._
	
	class RichPerson(person: Person) {
	    def toLabel(): JLabel = {
	        new JLabel(person.toString)
	    }
	    //.. other UI context extensions
	}
}


package org.dg.biz.ui {
	import org.dg.biz.core._
	trait UIFramework {
	    implicit def enrichPerson(person: Person): RichPerson = {
	        new RichPerson(person)
	    }
	}
}


package org.dg.biz.ui {
	import org.dg.biz.core._
	import java.awt.event.WindowAdapter
	import javax.swing._
	
	object Main extends UIFramework {
	
	    def main(args: Array[String]) = {
	        val p = new Person("ghosh", "debasish", 35)
	        val frame = new JFrame()
	        frame.addWindowListener(new WindowAdapter(){})
	        frame.getContentPane().add(p.toLabel) // nice person.toLabel() usage
	        frame.pack()
	        frame.setVisible(true)
	    }
	}
}

// Messaging Framework ======================================================
package org.dg.biz.msg {

	import org.dg.biz.core._
	
	class RichPerson(person: Person) {
	    def toXML(): scala.xml.Elem = {
	        <person>
	            <name>
	                <lastname>{person.lastName}</lastname>
	                <firstname>{person.firstName}</firstname>
	            </name>
	            <age>{person.age}</age>
	        </person>
	    }
	}
}


package org.dg.biz.msg {
	import org.dg.biz.core._;
	
	trait MessageFramework {	
	    implicit def enrichPerson(person: Person): RichPerson = {
	        new RichPerson(person)
	    }
	}
}


package org.dg.biz.msg {
	import org.dg.biz.core._
	
	object Main extends MessageFramework {
	
	    def save(person: scala.xml.Elem) = {
	        scala.xml.XML.saveFull("person.xml", person, "UTF8", true, null)
	    }
	
	    def main(args: Array[String]) = {
	        val p = new Person("ghosh", "debasish", 41)
	        save(p.toXML)
	    }
	}
}


