package repositories.common.util.payroll.salary

import conf.connection.DataConnection
import domain.payroll.salary.Notch
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.payroll.salary.NotchRepositor
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class NotchRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    NotchRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val notch = Notch(
      "G100",
      "N100",
      "Grade Name",
      999.12345)

    val result = Await.result(NotchRepository.save(notch), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetNotch") {
    val result = Await.result(NotchRepository.getGradeNotches("G100"), 2.minutes)
    assert(result.head.id === "N100")
  }
  override protected def afterEach(): Unit = {
    NotchRepository.truncate().future()
  }
}