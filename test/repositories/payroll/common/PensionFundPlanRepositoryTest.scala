package repositories.common.util.payroll.common

import conf.connection.DataConnection
import domain.payroll.common.PensionFundPlan
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.payroll.common.PensionFundPlanRepository
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class PensionFundPlanRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    PensionFundPlanRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val details: Map[String, String] = Map()
    val pensionFundPlan = PensionFundPlan(
      "PF100",
      new DateTime(2016, 11, 9, 12, 0, 0, 0),
      details)

    val result = Await.result(PensionFundPlanRepository.save(pensionFundPlan), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetPensionFundPlan") {
    val result = Await.result(PensionFundPlanRepository.getPensionFundPlans("PF100"), 2.minutes)
    assert(result.head.date === new DateTime(2016, 11, 9, 12, 0, 0, 0))
  }

  test("testFindAllPensionFundPlan") {
    val result = Await.result(PensionFundPlanRepository.findAll, 2.minutes)
    assert( result.size > 0)
  }
  override protected def afterEach(): Unit = {
    PensionFundPlanRepository.truncate().future()
  }
}



