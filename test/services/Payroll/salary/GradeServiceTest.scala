package services.common.util.Payroll.salary

import app.services.payroll.salary.GradeService
import domain.payroll.salary.Grade
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class GradeServiceTest extends FunSuite {

  val details: Map[String, String] = Map()
  test("testSaveOrUpdate") {
    val grade = Grade("ORG100", "G100", "Grade Name", 12345, 0.12345, 999.12345, "US100", new DateTime(), "notes")
    val result = Await.result(GradeService.apply.createOrUpdate(grade), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGrade") {
    val result = Await.result(GradeService.apply.getCompanyGrades("ORG100"), 2.minutes)
    assert(result.head.topAmount === 999.12345)
  }
}