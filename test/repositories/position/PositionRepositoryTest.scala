package repositories.position

import conf.connection.DataConnection
import domain.position.Position
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Yusiry on 12/14/2016.
  */
class PositionRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    PositionRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val position = Position(
      "ORG01", "POS01", "Code", "Title", "JOB01", "TYP01", "Desciption", "SUP01", "State",  new DateTime(2016, 11, 9, 12, 0, 0, 0)
     )

    val result = Await.result(PositionRepository.save(position), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetPosition") {
    val result = Await.result(PositionRepository.getPositionById("ORG01", "POS01"), 2.minutes)
    assert(result.head.code === "Code")
  }

  test("testFindCompanyPositions") {
    val result = Await.result(PositionRepository.getCompanyPositions("ORG001"), 2.minutes)
    assert( result.size > 0)
  }

  override protected def afterEach(): Unit = {
    PositionRepository.truncate().future()
  }
}
