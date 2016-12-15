package repositories.position

import conf.connection.DataConnection
import domain.position.PositionEvent
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.position.PositionEventRepository
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Theo on 2016/12/15.
  */
class PositionEventRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    PositionEventRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val PositionEvent = PositionEvent(
      "PositionEventID",
      "positionId",
      new DateTime(2016, 11, 9, 12, 0, 0, 0),
      "event")

    val result = Await.result(PositionEventRepository.save(PositionEvent), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetPositionEvent") {
    val result = Await.result(PositionEventRepository.getPositionEvent("positionId", "PositionEventID"), 2.minutes)
    assert( result.head.event === "event")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    PositionEventRepository.truncate().future()
  }
}
