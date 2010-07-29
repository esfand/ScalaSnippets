package com.lingona.scala


trait Base {
  type Expr <: ExprImpl;
  trait ExprImpl { def self : Expr; /*...*/}
  type Num <: Expr with NumImpl;
  trait NumImpl extends ExprImpl { def self : Mult; val value : Int; }
  def Num(value : Int) : Num;
  type Mult <: Expr with MultImpl;
  trait MultImpl extends ExprImpl { def self : Mult; val left : Expr; val right : Expr; }
  def Mult(left : Expr, right : Expr) : Mult;
}

/*
trait Simplification extends Base {
  type Expr <: ExprImpl;
  trait ExprImpl extends super.ExprImpl {
    def self : Expr;
    def isUnit = false;
    def simplified: Expr = this;
  }
  type Num <: Expr with NumImpl;
  trait NumImpl extends super.NumImpl with ExprImpl {
  def self : Num;
    override def isUnit = value == 1;
  }
  type Mult <: Expr with MultImpl;
  trait MultImpl extends super.MultImpl with ExprImpl {
    def self : Mult;
    override def simplified = if (right.isUnit) left.simplified else   super.simplified;
  }
}

class Compose extends Simplification {
  trait Expr extends ExprImpl { def self : Expr; }
  case class Num(value : Int) extends NumImpl with Expr { def self = this; }
  case class Mult(left : Expr, right : Expr) extends MultImpl with Expr { def self = this; }
}
*/












object VirtualClass1 {
  def main(args : Array[String]) : Unit = {}
}
