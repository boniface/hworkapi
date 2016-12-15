package services.common.util.training.courses

import domain.common.util.Mail
import domain.training.courses.CourseStatus
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.Training.CourseStatusService
import services.common.util.MailService
import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by gavin.ackerman on 2016-12-15.
 */
class CourseStatusServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val courseStatus = CourseStatus(
      "course234",
      "active",
      new DateTime(2016, 11, 9, 12, 0, 0, 0)
    )

    val result = Await.result(CourseStatusService.apply.createOrUpdate(courseStatus), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourseStatus") {
    val result = Await.result(CourseStatusService.apply.getCourseStatusById("course234"), 2.minutes)
    assert( result.head.status === "active")
  }

}
