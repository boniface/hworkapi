package factories.training.schedules

import domain.training.schedules.CourseParticipants
import org.scalatest.FunSuite
/**
 * Created by gavin.ackerman on 2016-10-23.
 */
class CourseParticipantsFactoryTest extends FunSuite {
  test("testCreateCourseParticipants")
  {
    val values = Map("scheduledCourseId"->"1000", "userId"->"2000")
    val courseParticipants = CourseParticipantsFactory.createCourseParticipants(values)
    assert(courseParticipants == CourseParticipants(scheduledCourseId="1000", userId="2000"))
  }
}
