package repositories.common.util.training.courses

import conf.connection.DataConnection
import domain.training.courses.{CourseStatus, Course}
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.training.courses.{CourseStatusRepository, CourseRepository}
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by gavin.ackerman on 2016-12-14.
 */
class CourseStatusRepositoryTest extends FunSuite   with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    CourseStatusRepository.create.ifNotExists().future()
  }
 // CourseStatus(courseId: String, status: String, date: DateTime)
  test("testSaveOrUpdate") {
    val courseStatus = CourseStatus(
      "course234",
      "active",
      new DateTime(2016, 11, 9, 12, 0, 0, 0)
    )

    val result = Await.result(CourseStatusRepository.save(courseStatus), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourseStatus") {
    val result = Await.result(CourseStatusRepository.getCourseStatusById("course234"), 2.minutes)
    assert( result.head.status === "active")
  }
  override protected def afterEach(): Unit = {
    //Delete All records
    CourseStatusRepository.truncate().future()
  }
}
