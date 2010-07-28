package com.lingona.scala

/*
import org.scalatest.testng.TestNGSuite
import org.scalatest.matchers.ShouldMatchers
import scala.collection.mutable.ListBuffer
import org.testng.Assert._
import org.testng.annotations.Test
import org.testng.annotations.Configuration

class ExampleSuite extends TestNGSuite with ShouldMatchers {

  var sb: StringBuilder = _
  var lb: ListBuffer[String] = _

//  @Configuration { val beforeTestMethod = true }
  def initialize() {
    sb = new StringBuilder("ScalaTest is ")
    lb = new ListBuffer[String]
  }

  @Test 
  def verifyEasy() { // Uses ScalaTest assertions
    sb.append("easy!")
    assert(sb.toString === "ScalaTest is easy!")
    assert(lb.isEmpty)
    lb += "sweet"
    intercept[StringIndexOutOfBoundsException] {
      "concise".charAt(-1)
    }
  }

  @Test 
  def verifyFun() { // Uses ScalaTest matchers
    sb.append("fun!")
    sb.toString should be ("ScalaTest is fun!")
    lb should be ('empty)
    lb += "sweet"
    evaluating { "concise".charAt(-1) } should produce [StringIndexOutOfBoundsException]
  }
}
*/