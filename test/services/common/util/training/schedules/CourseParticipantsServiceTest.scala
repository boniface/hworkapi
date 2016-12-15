package services.common.util.training.schedules

import domain.common.util.Mail
import domain.training.schedules.CourseParticipants
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.Training.CourseParticipantsService
import services.common.util.MailService
import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by gavin.ackerman on 2016-12-15.
 */
class CourseParticipantsServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val courseParticipants = CourseParticipants(
      "sched63",
      "user534"

    )

    val result = Await.result(CourseParticipantsService.apply.createOrUpdate(courseParticipants), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourseParticipants") {
    val result = Await.result(CourseParticipantsService.apply.getCourseParticipantsById("sched63","user534"), 2.minutes)
    assert( result.head.scheduledCourseId === "sched63")
  }
}
