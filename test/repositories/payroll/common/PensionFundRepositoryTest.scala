package repositories.common.util.payroll.common

import conf.connection.DataConnection
import domain.payroll.common.PensionFund
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.payroll.common.PensionFundRepository
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class PensionFundRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    PensionFundRepository.create.ifNotExists().future()
}
  test("testSaveOrUpdate") {
  val pensionFund = PensionFund(
  "ORG100",
  "PF100",
  "Pension Fund Name")

  val result = Await.result(PensionFundRepository.save(pensionFund), 2.minutes)
  assert(result.isExhausted)
}

  test("testGetPensionFund") {
  val result = Await.result(PensionFundRepository.getPensionFund("ORG100"), 2.minutes)
  assert(result.head.pensionFundId === "PF100")
}

  test("testFindAllPensionFund") {
  val result = Await.result(PensionFundRepository.findAll, 2.minutes)
  assert( result.size > 0)
}
  override protected def afterEach(): Unit = {
    PensionFundRepository.truncate().future()
}
}
