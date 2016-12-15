package repositories.position

import conf.connection.DataConnection
import domain.position.DepartureReason
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.position.DepartureReasonRepository
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Theo on 2016/12/15.
  */
class DepartureReasonRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    DepartureReasonRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val DepartureReason = DepartureReason(
      "organisationId",
      "departureReasonId",
      "reason",
      "description",
      "state")

    val result = Await.result(DepartureReasonRepository.save(DepartureReason), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetDepartureReason") {
    val result = Await.result(DepartureReasonRepository.getDepartureReason("organisationId","departureReasonId"), 2.minutes)
    assert( result.head.reason === "reason")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    DepartureReasonRepository.truncate().future()
  }
}
