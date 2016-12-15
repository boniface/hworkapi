package services.common.util.training.schedules

import domain.common.util.Mail
import domain.training.schedules.CourseRating
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.Training.CourseRatingService
import services.common.util.MailService
import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by gavin.ackerman on 2016-12-15.
 */
class CourseRatingServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val courseRating = CourseRating(
      "org63",
      "sched534",
      34,
      "blah"
    )

    val result = Await.result(CourseRatingService.apply.createOrUpdate(courseRating), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourseRating") {
    val result = Await.result(CourseRatingService.apply.getCourseRatingById("org63","sched534"), 2.minutes)
    assert( result.head.rating === 34)
  }

}
