package services.common.util.training.schedules

import domain.common.util.Mail
import domain.training.schedules.CourseInstructors
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.Training.CourseInstructorsService
import services.common.util.MailService
import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by gavin.ackerman on 2016-12-15.
 */
class CourseInstructorsServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val courseInstructors = CourseInstructors(
      "sched63",
      "train534"

    )

    val result = Await.result(CourseInstructorsService.apply.createOrUpdate(courseInstructors), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourseInstructors") {
    val result = Await.result(CourseInstructorsService.apply.getCourseInstructorsById("sched63","train534"), 2.minutes)
    assert( result.head.TrainingInstructorId === "train534")
  }

}
