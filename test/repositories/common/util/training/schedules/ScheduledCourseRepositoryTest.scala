package repositories.common.util.training.schedules

import conf.connection.DataConnection
import domain.training.schedules.{ScheduledCourse, CourseRating}
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.training.schedules.{ScheduledCourseRepository, CourseRatingRepository}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
/**
 * Created by gavin.ackerman on 2016-12-14.
 */
class ScheduledCourseRepositoryTest extends FunSuite   with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    ScheduledCourseRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val scheduledCourse = ScheduledCourse(
      "org63",
      "course534",
      "sched453",
      "room6",
    12,
    56,
      new DateTime(2016, 11, 9, 12, 0, 0, 0),
      new DateTime(2017, 11, 9, 12, 0, 0, 0),
    "loc23",
      new DateTime(2018, 11, 9, 12, 0, 0, 0)
    )

    val result = Await.result(ScheduledCourseRepository.save(scheduledCourse), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetScheduledCourse") {
    val result = Await.result(ScheduledCourseRepository.getScheduledCourseById("org63","course534","sched453"), 2.minutes)
    assert( result.head.creditHours === 56)
  }
  override protected def afterEach(): Unit = {
    //Delete All records
    ScheduledCourseRepository.truncate().future()
  }

}
