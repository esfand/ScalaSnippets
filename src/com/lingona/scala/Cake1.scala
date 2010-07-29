package com.lingona.scala {

//	import org.specs.mock._
//	import org.specs.mock.JMocker._
//	import org.specs.mock.JMocker.{expect => expecting}

//	import org.scalatest.Suite
//	import org.scalatest.testng._
//	import org.scalatest.mock
//	import org.scalatest.testng.TestNGSuite
//	import org.scalatest.FlatSpec
//	import org.scalatest.matchers.ShouldMatchers

//	import org.testng.annotations.Test
//	import org.testng.annotations.Configuration
	

	class User(val username: String, val password: String) {}
	

	// The component namespace for our repository.
	trait UserRepositoryComponent {
		
		val userRepository: UserRepository
		
		// a dummy service that is not persisting anything, just prints out info
		class UserRepository {
			def authenticate(username: String, password: String): User = {
				var user = new User(username, password)
				println("authenticating user: " + user)
				user
			}
			def create(user: User) = println("creating user: " + user)
			def delete(user: User) = println("deleting user: " + user)
		}
	}

	
	// using self-type annotation declaring the dependencies this
	// component requires, in our case the UserRepositoryComponent  
	trait UserServiceComponent {
		
		module: UserRepositoryComponent =>
		
		val userService: UserService
		
		class UserService {
			
			def authenticate(username: String, password: String): User = 
				module.userRepository.authenticate(username, password)  
		
			def create(username: String, password: String) = 
				userRepository.create(new User(username, password))
		
			def delete(user: User) = 
				userRepository.delete(user)
		}
	}

	// merge/join the different namespaces into one single application (or module) namespace.
	// abstract away the actual component instantiation as well as the 
	// wiring into this single “configuration” object.	
	object ComponentRegistry extends UserServiceComponent 
							 with    UserRepositoryComponent
	{
		val userService    = new UserService
		val userRepository = new UserRepository		
	}

/*	
	trait TestEnvironment extends UserServiceComponent    with 
								  UserRepositoryComponent with 
								  JMocker
	{
		val userRepository = mock[UserRepository]
		val userService    = mock[UserService]
	}
*/

/*	
	class UserServiceSuite extends TestNGSuite with TestEnvironment {
	
	  @Test { val groups=Array("unit") }
	  def authenticateUser = {
	
	    // create a fresh and clean (non-mock) UserService 
	    // (who's userRepository is still a mock)
	    val userService = new UserService
	
	    // record the mock invocation
	    JMocker.expect {
	      val user = new User("test", "test")
	      one(userRepository).authenticate("test", "test") willReturn user
	    }
	    
	    //... // test the authentication method
	  }
	  
	  //...

	}	
*/	
	
	object Cake1 {
		def main(args : Array[String]) : Unit = {
			val userService = ComponentRegistry.userService
			//...
			val user = userService.authenticate("Esfand", "Minkie123") 			
		}
	}
}