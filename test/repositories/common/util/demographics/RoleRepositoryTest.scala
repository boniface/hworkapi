package repositories.common.util.demographics

/**
  * Created by Aphiwe on 2016/12/13.
  */

import conf.connection.DataConnection
import domain.common.demographics.{MaritalStatus, Race, Role}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.demographics.{MaritalStatusRepository, RaceRepository, RoleRepository}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class RoleRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    RoleRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val role = Role(
      "RoleID",
      "Tester","Testing Programmes")

    val result = Await.result(RoleRepository.save(role), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetRole") {
    val result = Await.result(RoleRepository.getRoleById("RoleID"), 2.minutes)
    assert( result.head.name === "Tester")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    RoleRepository.truncate().future()
  }

}
