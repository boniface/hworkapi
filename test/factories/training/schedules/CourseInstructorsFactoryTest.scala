package factories.training.schedules

import domain.training.schedules.CourseInstructors
import org.scalatest.FunSuite
/**
 * Created by gavin.ackerman on 2016-10-23.
 */
class CourseInstructorsFactoryTest extends FunSuite {
  test("testCreateCourseInstructors")
  {
    val values = Map("scheduledCourseId"->"1000", "TrainingInstructorId"->"Accounting")
    val courseInstructors = CourseInstructorsFactory.createCourseInstructors(values)
    assert(courseInstructors == CourseInstructors(scheduledCourseId="1000", TrainingInstructorId="Accounting"))
  }
}
