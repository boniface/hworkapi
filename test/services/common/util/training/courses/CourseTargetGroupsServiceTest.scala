package services.common.util.training.courses

import domain.common.util.Mail
import domain.training.courses.CourseTargetGroups
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.Training.CourseTargetGroupService
import services.common.util.MailService
import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by gavin.ackerman on 2016-12-15.
 */
class CourseTargetGroupsServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val courseTargetGroups = CourseTargetGroups(
      "org53",
      "course324",
      "targetgroup532"
    )


    val result = Await.result(CourseTargetGroupService.apply.createOrUpdate(courseTargetGroups), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourseTargetGroups") {
    val result = Await.result(CourseTargetGroupService.apply.getCourseTargetGroupById("org53","course324","targetgroup532"), 2.minutes)
    assert( result.head.targetGroupId === "targetgroup532")
  }

}
