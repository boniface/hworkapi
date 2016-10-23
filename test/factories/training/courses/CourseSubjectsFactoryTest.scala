package factories.training.courses
import org.scalatest.FunSuite
import domain.training.courses.CourseSubjects

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
class CourseSubjectsFactoryTest extends FunSuite {
  test("testCreateCourseSubjects")
  {
    val values = Map("organisationId"->"1000", "courseId"->"2000", "subjectId"->"2000")
    val courseSubjects = CourseSubjectsFactory.createCourseSubjects(values)
    assert(courseSubjects == CourseSubjects(organisationId="1000", courseId="2000",subjectId="2000"))
  }
}
