package services.common.util.training.courses

import domain.common.util.Mail
import domain.training.courses.CourseCompetencies
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.Training.CourseCompetenciesService
import services.common.util.MailService
import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by gavin.ackerman on 2016-12-15.
 */
class CourseCompetenciesServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val courseCompetencies = CourseCompetencies(
      "org53",
      "course324",
      "comp443"

    )

    val result = Await.result(CourseCompetenciesService.apply.createOrUpdate(courseCompetencies), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourseCompetencies") {
    val result = Await.result(CourseCompetenciesService.apply.getCourseCompetenciesById("org53","course324"), 2.minutes)
    assert( result.head.courseId === "course324")
  }

}
