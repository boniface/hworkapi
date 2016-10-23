package factories.training.schedules

import domain.training.schedules.CourseRating
import org.scalatest.FunSuite
/**
 * Created by gavin.ackerman on 2016-10-23.
 */
class CourseRatingFactoryTest extends FunSuite {
  test("testCreateCourseRating")
  {
    val values = Map("organisationId"->"1000", "scheduledCourseId"->"3000","comment"->"1000")
    val rat:Int = 20
    val courseRating = CourseRatingFactory.createCourseRating(values,rat)

    assert(courseRating == CourseRating(organisationId="1000", scheduledCourseId="3000", rating = 20,comment="1000"))
  }
}