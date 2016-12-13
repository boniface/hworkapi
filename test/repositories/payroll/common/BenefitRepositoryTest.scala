package repositories.common.util.payroll.common

import conf.connection.DataConnection
import domain.payroll.common.Benefit
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.payroll.common.BenefitRepository
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class BenefitRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    BenefitRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val benefit = Benefit(
      "ORG100",
      "BN100",
      "Benefit Name",
      new DateTime(2016, 11, 9, 12, 0, 0, 0),
      "State")

    val result = Await.result(BenefitRepository.save(benefit), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetBenefit") {
    val result = Await.result(BenefitRepository.getBenefit("ORG100"), 2.minutes)
    assert(result.head.name === "Benefit Name")
  }

  test("testFindAllBenefit") {
    val result = Await.result(BenefitRepository.findAll, 2.minutes)
    assert( result.size > 0)
  }
  override protected def afterEach(): Unit = {
    BenefitRepository.truncate().future()
  }
}

