

object TradeCore {
	case class Customer(id: Int, name: String)
	case object nomura extends Customer(1, "NOMURA")
	case object meryll extends Customer(2, "MERYLL")
	case object lehman extends Customer(3, "LEHMAN")
	
	case class Instrument(id: Int)
	case object ibm    extends Instrument(1)
	case object sun    extends Instrument(2)
	case object google extends Instrument(3)
	
	case class Trade(ref: Int, ins: Instrument, qty: Int, price: Int)
}

package com.lingona.scala {
	import TradeCore._
	
	object TradeApp {
/*		
		def flatMap[resColl[x] <: Iterable[resColl, x], s](f: t => resColl[s])(implicit buf: Accumulator[resColl, s]): resColl[s] = {
			//..
		}
*/		
		def main(args : Array[String]) : Unit = {
			
			val employees: List[Option[String]] =
				List(Some("dave"), None, Some("john"), Some("sam"))
			
			val n : Int /*List[Int]*/ =
				employees.map { x =>
					x match {
						case Some(name) => name.length
						case None => 0
					}
				}.elements.reduceLeft[Int](_ + _)
				
			println(n)
			
			
			val brs: List[List[String]] =
				List(List("dave", "john", "sam"), List("peter", "robin", "david"), List("pras", "srim"))
			
			val m: Int/*List[Int]*/ =
				brs.flatMap {x => x.map {_.length}}
					.elements.reduceLeft[Int](_ + _)
					
			println(m)
			
/*			
			val l:   List[Int] = employees.flatMapTo[Int, List]{_.map{_.length}}
			val sum: Int       = l.elements.reduceLeft[Int](_ + _)
			println(sum)
*/			
			
			

			val trades: List[(Customer, Option[Trade])] =
				List( (nomura, Some(Trade(100, ibm, 20, 12))),
					  (meryll, None), 
					  (lehman, Some(Trade(200, google, 10, 10))))
/*			
			val ts: List[Option[Trade]] = trades.map(_._2)
			val t:  List[Int]           = ts.flatMapTo[Int, List]{_.map{x => x.qty * x.price}}
			val value                   = t.elements.reduceLeft[Int](_ + _)
			
			println(value)
*/			
		}
	}
}
