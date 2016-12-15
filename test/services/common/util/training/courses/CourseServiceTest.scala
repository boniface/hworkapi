package services.common.util.training.courses

import domain.common.util.Mail
import domain.training.courses.Course
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.Training.CourseRepositoryService
import services.common.util.MailService
import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by gavin.ackerman on 2016-12-15.
 */
class CourseServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val course = Course(
      "org53",
      "course324",
      "IT",
      "coursecat343",
      "coursecode535",
      "train43",
      "courseob-basics",
      "coursetype464",
      "criteria83",
      "desc-fundamentals of IT"
    )

    val result = Await.result(CourseRepositoryService.apply.createOrUpdate(course), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourse") {
    val result = Await.result(CourseRepositoryService.apply.getCourseById("org53","course324","coursecode535"), 2.minutes)
    assert( result.head.description === "desc-fundamentals of IT")
  }

}
