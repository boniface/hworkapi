package services.common.util.training.courses

import domain.common.util.Mail
import domain.training.courses.CourseCategory
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.Training.CourseCategoryService
import services.common.util.MailService
import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by gavin.ackerman on 2016-12-15.
 */
class CourseCategoryServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val courseCategory = CourseCategory(
      "org13",
      "coursecat23",
      "design"

    )

    val result = Await.result(CourseCategoryService.apply.createOrUpdate(courseCategory), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourseCategory") {
    val result = Await.result(CourseCategoryService.apply.getCourseCategoryById("org13","coursecat23"), 2.minutes)
    assert(result.head.name === "design")
  }

}
