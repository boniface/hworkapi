package repositories.common.util.demographics

/**
  * Created by Aphiwe on 2016/12/13.
  */

import conf.connection.DataConnection
import domain.common.demographics.{MaritalStatus, Race, Role, Title}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.demographics.{MaritalStatusRepository, RaceRepository, RoleRepository, TitleRepository}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class TitleRepositotyTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    TitleRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val title = Title(
      "TitleID",
      "MR")

    val result = Await.result(TitleRepository.save(title), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetTitle") {
    val result = Await.result(TitleRepository.getTitleById("TitleID"), 2.minutes)
    assert( result.head.name === "MR")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    TitleRepository.truncate().future()
  }

}
