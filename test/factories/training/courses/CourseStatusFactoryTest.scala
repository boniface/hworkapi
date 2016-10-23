package factories.training.courses
import org.scalatest.FunSuite
import java.util.Date
import domain.training.courses.CourseStatus
import factories.training.courses.CourseStatusFactory
/**
 * Created by gavin.ackerman on 2016-10-23.
 */

class CourseStatusFactoryTest extends FunSuite {
  test("testCreateCourseStatus")
  {
    val values = Map("courseId"->"1000", "status"->"2000")
    val dates = new Date ()
    val courseStatus = CourseStatusFactory.createCourseStatus(values,dates)
    assert(courseStatus == CourseStatus(courseId="1000", "status"->"2000",date = dates))
  }
}
