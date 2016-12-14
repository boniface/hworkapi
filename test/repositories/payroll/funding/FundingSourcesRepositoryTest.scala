package repositories.common.util.payroll.funding

import conf.connection.DataConnection
import domain.payroll.funding.FundingSources
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.payroll.funding.FunderRepository
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class FundingSourcesRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    FunderRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val details: Map[String, String] = Map()
    val fundingSources = FundingSources(
      "ORG100",
      "FS100",
      "FundingSource",
      "12345",
      new DateTime(2016, 11, 9, 12, 0, 0, 0),
      details)

    val result = Await.result(FunderRepository.save(fundingSources), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetFundingSources") {
    val result = Await.result(FunderRepository.getFunder("ORG100"), 2.minutes)
    assert(result.head.fundingSourcesId === "FS100")
  }

  test("testFindAllFundingSources") {
    val result = Await.result(FunderRepository.findAll, 2.minutes)
    assert( result.size > 0)
  }
  override protected def afterEach(): Unit = {
    FunderRepository.truncate().future()
  }
}



