
package structuraltyping {
	object Exposed {
		case class Employee   (id: Int) { def salary: Int = 0 } //.. }
		case class DailyWorker(id: Int) { def salary: Int = 0 } //.. }
		case class Contractor (id: Int) { def wage:   Int = 0 } //.. }
	
		case class Salaried(salary: Int)
		implicit def wageToSalary(in: {def wage: Int}) = Salaried(in.wage)
	
		class Payroll {
		  def makeSalarySheet[T <% { def salary: Int }] (employees: List[T]) = {
		    (0 /: employees)(_ + _.salary)
		  }
		}
	}
}


package com.lingona.scala {
	import structuraltyping._
	import Exposed._

	object StructuralTyping {
		def main(args : Array[String]) : Unit = {
	 	  
			val l = List[{ def salary: Int }](DailyWorker(1), Employee(2), Employee(1), Contractor(9))		
			val p = new Payroll
			println(p.makeSalarySheet(l))
		
	  }
	}
}