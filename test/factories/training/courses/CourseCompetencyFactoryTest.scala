package factories.training.courses
import org.scalatest.FunSuite
import domain.training.courses.CourseCompetencies

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
class CourseCompetenciesFactoryTest extends FunSuite {
  test("testCreateCourseCompetencies")
  {
    val values = Map("organisationId"->"1000", "courseId"->"2000", "compentencyId"->"3000")

    val courseCompetencies = CourseCompetenciesFactory.createCourseCompetencies(values)
    assert(courseCompetencies == CourseCompetencies(organisationId="1000", "competencyTypeId"->"2000", name="3000"))
  }
}