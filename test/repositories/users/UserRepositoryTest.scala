package repositories.users


import conf.connection.DataConnection
import domain.users.User
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Lonwabo on 12/13/2016.
  */
class UserRepositoryTest extends FunSuite with BeforeAndAfterEach {

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override def beforeEach() {
    UserRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate"){
    val user = User(
      "123",
      "Lu100",
      "Lutho",
      Option("Dazz"),
      "lltsipa@gmail.com",
      "Mjoli",
      "Mr",
      "2367",
      true,
      false,
      false,
      true,
      "active"
    )

    val result = Await.result(UserRepository.save(user), 2.minutes)
    assert(result.isExhausted)

  }

  test("testGetUser"){
    val result = Await.result(UserRepository.getUser("123","Lu100"), 2.minutes)
    assert(result.head.email === "lltsipa@gmail.com")
  }
  override def afterEach() {
    UserRepository.truncate().future()
  }
}
