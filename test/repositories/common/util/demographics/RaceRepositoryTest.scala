package repositories.common.util.demographics

/**
  * Created by Aphiwe on 2016/12/13.
  */

import conf.connection.DataConnection
import domain.common.demographics.{MaritalStatus, Race}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.demographics.{MaritalStatusRepository, RaceRepository}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class RaceRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    RaceRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val race = Race(
      "RaceID",
      "African")

    val result = Await.result(RaceRepository.save(race), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetRace") {
    val result = Await.result(RaceRepository.getRace("raceID"), 2.minutes)
    assert( result.head.name === "African")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    RaceRepository.truncate().future()
  }
}
