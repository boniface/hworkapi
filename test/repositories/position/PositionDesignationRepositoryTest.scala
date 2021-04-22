package repositories.position

import conf.connection.DataConnection
import domain.position.PositionDesignation
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.position.PositionDesignationRepository
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Theo on 2016/12/15.
  */
class PositionDesignationRepositoryTest extends FunSuite with BeforeAndAfterEach {

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    PositionDesignationRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val PositionDesignation = PositionDesignation(
      "positionId",
      "positionDesignationId",
      new DateTime(2016, 11, 9, 12, 0, 0, 0),
      "designationId",
      "state")

    val result = Await.result(PositionDesignationRepository.save(PositionDesignation), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetPositionDesignation") {
    val result = Await.result(PositionDesignationRepository.getDesignationById("positionId", "positionDesignationId"), 2.minutes)
    assert(result.head.state === "state")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    PositionDesignationRepository.truncate().future()
  }
}