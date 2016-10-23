package factories.training.courses
import org.scalatest.FunSuite
import domain.training.courses.CourseCategory
import factories.training.courses.CourseCategoryFactory
/**
 * Created by gavin.ackerman on 2016-10-23.
 */
class CourseCategoryFactoryTest extends FunSuite {
  test("testCreateCourseCategory")
  {
    val values = Map("organisationId"->"1000", "courseCategoryId"->"2000", "name"->"Medium")

    val courseCategory = CourseCategoryFactory.createCourseCategory(values)
    assert(courseCategory == CourseCategory(organisationId="1000", courseCategoryId="2000", name="Medium"))
  }
}
