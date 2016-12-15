package repositories.users

import conf.connection.DataConnection
import domain.users.UserRole
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by SONY on 2016-12-15.
  */
class UserRoleRepositoryTest extends FunSuite with BeforeAndAfterEach {

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    UserImagesRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate"){
    val userRole = UserRole("ORG100",
      "FS100",
      "P10",
      "RSA",
      new DateTime)

    val result = Await.result(UserRoleRepository.save(userRole), 2.minutes)
    assert(result.isExhausted)
  }

  test("getUserRole"){
    val result = Await.result(UserRoleRepository.getOrganisationRoles("ORG123"), 2.minutes)
    assert(result.head.organisationId == "SOU123")
  }

  override protected def afterEach(): Unit = {
    UserRoleRepository.truncate().future()
  }
}
