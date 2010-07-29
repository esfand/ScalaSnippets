package com.lingona.scala

object StructuralType1 {
	
	class File(name: String) {
		def getName(): String = name
		def open() { /*..*/ }
		def close() { println("close file") }
	}
	
	def test(f: { def getName(): String }) { println(f.getName) }
		
	def main(args : Array[String]) : Unit = {
		test(new File("test.txt"))
		test(new java.io.File("test.txt"))
	}
}
