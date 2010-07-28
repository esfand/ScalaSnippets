package com.lingona.scala

object JavaArray {
	def main(args : Array[String]) : Unit = {
		
	  //import scala.collection.JavaConversions._
		
		var javaList = new java.util.ArrayList[Int]()                                       
	//  javaList: java.util.ArrayList[Int] = []
	
		javaList.add(3)                                                                     
	//  res0: Boolean = true
	
		javaList.add(4)
	//  res1: Boolean = true
	
		javaList.add(5)
	//  res2: Boolean = true
	
		val scalaSeq = scala.collection.JavaConversions.asBuffer(javaList) 
	//  scalaSeq: scala.collection.mutable.Buffer[Int] = Buffer(3, 4, 5)
	
		println(javaList)
	//  [3, 4, 5]
	
		println(scalaSeq)
	//  Buffer(3, 4, 5)	
	}
}
