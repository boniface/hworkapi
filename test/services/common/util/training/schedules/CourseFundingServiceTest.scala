package services.common.util.training.schedules

import domain.common.util.Mail
import domain.training.schedules.CourseFunding
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.Training.CourseFundingService
import services.common.util.MailService
import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by gavin.ackerman on 2016-12-15.
 */
class CourseFundingServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val courseFunding = CourseFunding(
      "sched63",
      "fund534",
      54,
      "currency45"
    )

    val result = Await.result(CourseFundingService.apply.createOrUpdate(courseFunding), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourseFunding") {
    val result = Await.result(CourseFundingService.apply.getCourseFundingById("course324","fund534"), 2.minutes)
    assert( result.head.amount === 54)
  }

}
