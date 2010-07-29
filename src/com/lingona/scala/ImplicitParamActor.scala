
package com.lingona.scala {
	
	class Actor {
		def receive: Array[Byte] = null
		def reply:   Array[Byte] = null
	}
	
	class ActorRef {
	
	}
	
	/**
	 * Type class definition for Actor Serialization
	 */
	trait FromBinary[T <: Actor] {
		def fromBinary(bytes: Array[Byte], act: T): T
	}
	
	trait ToBinary[T <: Actor] {
		def toBinary(t: T): Array[Byte]
	}
	
	// client needs to implement Format[] for the respective actor
	trait Format[T <: Actor] extends FromBinary[T] with ToBinary[T]	
	
	
	//
	// Module for actor serialization
	//
	object ActorSerialization {
	
		def fromBinary[T <: Actor](bytes: Array[Byte])(implicit format: Format[T]): ActorRef = {null} //..
	
		def toBinary[T <: Actor](a: ActorRef)(implicit format: Format[T]): Array[Byte] = null //..
	
		//.. implementation
	}	

/*	
	//
	// A sample actor with encapsulated state.
	// Not inherited from any specialized Actor class
	//
	class MyActor extends Actor {
		var count = 0
	
		def receive = {
			case "hello" =>
				count = count + 1
				self.reply("world " + count)
		}
	}
*/
	
/*
	object BinaryFormatMyActor {
		implicit object MyActorFormat extends Format[MyActor] {
			def fromBinary(bytes: Array[Byte], act: MyActor) = {
				val p = Serializer.Protobuf
	                        .fromBinary(bytes, Some(classOf[ProtobufProtocol.Counter]))
	                        .asInstanceOf[ProtobufProtocol.Counter]
	            act.count = p.getCount
	            act
			}
			def toBinary(ac: MyActor) =
				ProtobufProtocol.Counter.newBuilder.setCount(ac.count).build.toByteArray
		}
	}	
*/	

/*
	object DependencyInjection1 {
		import ActorSerialization._
		import BinaryFormatMyActor._
		
		def main(args : Array[String]) : Unit = {

			val actor1 = actorOf[MyActor].start
			(actor1 !! "hello").getOrElse("_") should equal("world 1")
			(actor1 !! "hello").getOrElse("_") should equal("world 2")
			
			val bytes = toBinary(actor1)
			val actor2 = fromBinary(bytes)
			actor2.start
			(actor2 !! "hello").getOrElse("_") should equal("world 3")			
		}
	}
*/
}