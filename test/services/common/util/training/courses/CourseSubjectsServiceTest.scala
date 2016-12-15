package services.common.util.training.courses

import domain.common.util.Mail
import domain.training.courses.CourseSubjects
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.Training.CourseSubjectService
import services.common.util.MailService
import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by gavin.ackerman on 2016-12-15.
 */
class CourseSubjectsServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val courseSubjects = CourseSubjects(
      "org53",
      "course324",
      "sub443"
    )

    val result = Await.result(CourseSubjectService.apply.createOrUpdate(courseSubjects), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourseSubjects") {
    val result = Await.result(CourseSubjectService.apply.getCourseSubjectById("org53","course324","sub443"), 2.minutes)
    assert( result.head.subjectId === "sub443")
  }

}
