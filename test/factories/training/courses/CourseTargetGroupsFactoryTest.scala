package factories.training.courses
import org.scalatest.FunSuite
/**
 * Created by gavin.ackerman on 2016-10-23.
 */
import domain.training.courses.CourseTargetGroups
import factories.training.courses.CourseCategoryFactory
class CourseTargetGroupsFactoryTest extends FunSuite {
  test("testCreateCourseTargetGroups")
  {
    val values = Map("organisationId"->"1000", "courseId"->"2000", "targetGroupId"->"2000")
    val courseTarget = CourseTargetGroupsFactory.createCourseTargetGroups(values)
    assert(courseTarget == CourseTargetGroups(organisationId="1000", courseId="2000",targetGroupId="2000"))
  }
}
