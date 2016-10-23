package factories.training.schedules

import java.util.Date
import org.scalatest.FunSuite
import domain.training.schedules.ScheduledCourse

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
class ScheduledCourseFactoryTest extends FunSuite {
  test("testCreateScheduledCourse")
  {
    val values = Map("organisationId"->"1000", "courseId"->"3000","scheduledCourseId"->"1000","venue"->"1000","locationId"->"1000","name"->"1000")
    val hour:Int = 20
    val cap :Int = 30
    val start = new Date(2016,4,12)
    val end = new Date(2016,4,12)
    val sched = new Date(2016,4,12)
    val scheduledCourse = ScheduledCourseFactory.createScheduledCourse(values,hour,cap,start,end,sched)

    assert(scheduledCourse == ScheduledCourse(organisationId="1000", courseId="3000",scheduledCourseId="1000",courseCapacity = 30, creditHours = 30, startDate =start, endDate = end,venue = "1000",locationId = "1000",dateScheduled = sched))
  }
}
