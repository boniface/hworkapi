package repositories.position

import conf.connection.DataConnection
import domain.position.PositionOccupants
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.position.PositionOccupantsRepository
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Theo on 2016/12/15.
  */
class PositionOccupantsTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    PositionOccupantsRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val PositionOccupants = PositionOccupants(
      "PositionOccupants",
      "positionOccupantId",
      new DateTime(2016, 11, 9, 12, 0, 0, 0),
      "userId",
      "state")

    val result = Await.result(PositionOccupantsRepository.save(PositionOccupants), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetPositionOccupants") {
    val result = Await.result(PositionOccupantsRepository.getPositionOccupant("positionId", "positionOccupantId"), 2.minutes)
    assert( result.head.name === "surbub")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    PositionOccupantsRepository.truncate().future()
  }
}
