package repositories.position

import conf.connection.DataConnection
import domain.position.PositionFunding
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Yusiry on 12/14/2016.
  */
class PositionFundingRepositoryTest extends FunSuite with BeforeAndAfterEach {

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    PositionFundingRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate"){
    val positionFunding = PositionFunding(
      "ORG123", "POS123", "SOU123", new DateTime(2016,11,9,12,0,0,1))

    val result = Await.result(PositionFundingRepository.save(positionFunding), 2.minutes)
    assert(result.isExhausted)
    }

  test("getPositionFunding"){
    val result = Await.result(PositionFundingRepository.getPositionFunding("POS123", "ORG123"), 2.minutes)
    assert(result.head.fundingSourcesId == "SOU123")
  }

  override protected def afterEach(): Unit = {
    PositionFundingRepository.truncate().future()
  }
}
