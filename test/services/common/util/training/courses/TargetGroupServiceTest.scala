package services.common.util.training.courses

import domain.common.util.Mail
import domain.training.courses.TargetGroup
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.Training.TargetGroupService
import services.common.util.MailService
import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by gavin.ackerman on 2016-12-15.
 */
class TargetGroupServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val targetGroup = TargetGroup(
      "target53",
      "high"
    )

    val result = Await.result(TargetGroupService.apply.createOrUpdate(targetGroup), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetTargetGroup") {
    val result = Await.result(TargetGroupService.apply.getTargetGroupById("target53"), 2.minutes)
    assert( result.head.name === "high")
  }

}
