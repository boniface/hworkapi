package repositories.common.util.training.schedules

import conf.connection.DataConnection
import domain.training.schedules.{CourseInstructors, CourseFunding}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.training.schedules.{CourseInstructorsRepository, CourseFundingRepository}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
/**
 * Created by gavin.ackerman on 2016-12-14.
 */
class CourseInstructorsRepositoryTest extends FunSuite   with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    CourseInstructorsRepository.create.ifNotExists().future()
  }
 // CourseInstructors(scheduledCourseId:String,
    //TrainingInstructorId:String)
  test("testSaveOrUpdate") {
    val courseInstructors = CourseInstructors(
      "sched63",
      "train534"

    )

    val result = Await.result(CourseInstructorsRepository.save(courseInstructors), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourseFunding") {
    val result = Await.result(CourseInstructorsRepository.getCourseInstructorsById("sched63","train534"), 2.minutes)
    assert( result.head.TrainingInstructorId === "train534")
  }
  override protected def afterEach(): Unit = {
    //Delete All records
    CourseInstructorsRepository.truncate().future()
  }
}
