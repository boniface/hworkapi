package services.common.util.training.courses

import domain.common.util.Mail
import domain.training.courses.CourseType
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.Training.CourseTypeService
import services.common.util.MailService
import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by gavin.ackerman on 2016-12-15.
 */
class CourseTypeServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val courseType = CourseType(
      "coursetypefulltime",
      "IT"
    )

    val result = Await.result(CourseTypeService.apply.createOrUpdate(courseType), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourseType") {
    val result = Await.result(CourseTypeService.apply.getCourseTypeById("course324"), 2.minutes)
    assert( result.head.name === "IT")
  }

}
