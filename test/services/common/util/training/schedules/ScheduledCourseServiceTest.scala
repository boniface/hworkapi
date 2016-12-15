package services.common.util.training.schedules

import domain.common.util.Mail
import domain.training.schedules.ScheduledCourse
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.Training.ScheduledCourseService
import services.common.util.MailService
import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by gavin.ackerman on 2016-12-15.
 */
class ScheduledCourseServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val scheduledCourse = ScheduledCourse(
      "org63",
      "course534",
      "sched453",
      "room6",
      12,
      56,
      new DateTime(2016, 11, 9, 12, 0, 0, 0),
      new DateTime(2017, 11, 9, 12, 0, 0, 0),
      "loc23",
      new DateTime(2018, 11, 9, 12, 0, 0, 0)
    )

    val result = Await.result(ScheduledCourseService.apply.createOrUpdate(scheduledCourse), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetScheduledCourse") {
    val result = Await.result(ScheduledCourseService.apply.getScheduledCourseById("org63","course534","sched453"), 2.minutes)
    assert( result.head.creditHours === 56)
  }

}
