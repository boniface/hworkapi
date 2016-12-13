package repositories.common.util.payroll.salary

import conf.connection.DataConnection
import domain.payroll.salary.Grade
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.payroll.salary.GradeRepository
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */

class GradeRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    GradeRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val grade = Grade(
      "ORG100",
      "G100",
      "Grade Name",
      12345,
      0.12345,
      999.12345,
      "US100",
      new DateTime(2016, 11, 9, 12, 0, 0, 0),
      "notes")

    val result = Await.result(GradeRepository.save(grade), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetGrade") {
    val result = Await.result(GradeRepository.getCompanyGrades("ORG100"), 2.minutes)
    assert(result.head.topAmount === 999.12345)
  }
  override protected def afterEach(): Unit = {
    GradeRepository.truncate().future()
  }
}