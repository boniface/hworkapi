package factories.training.courses
import org.scalatest.FunSuite
/**
 * Created by gavin.ackerman on 2016-10-23.
 */
import domain.training.courses.CourseType
import factories.training.courses.CourseCategoryFactory
class CourseTypeFactoryTest extends FunSuite {
  test("testCreateCourseType")
  {
    val values = Map("courseTypeId"->"1000", "name"->"Accounting")
    val courseType = CourseTypeFactory.createCourseType(values)
    assert(courseType == CourseType(courseTypeId="1000", name="Accounting"))
  }
}
