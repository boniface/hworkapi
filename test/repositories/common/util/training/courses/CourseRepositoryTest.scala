package repositories.common.util.training.courses

import conf.connection.DataConnection
import domain.training.competencies.Competency
import domain.training.courses.Course
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.training.competencies.CompetencyRepository
import repositories.training.courses.CourseRepository
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by gavin.ackerman on 2016-12-14.
 */
class CourseRepositoryTest extends FunSuite   with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    CourseRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val course = Course(
      "org53",
      "course324",
      "IT",
      "coursecat343",
      "coursecode535",
      "train43",
      "courseob-basics",
      "coursetype464",
      "criteria83",
      "desc-fundamentals of IT"
    )

    val result = Await.result(CourseRepository.save(course), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourse") {
    val result = Await.result(CourseRepository.getCourseTypeById("org53","course324","coursecode535"), 2.minutes)
    assert( result.head.description === "desc-fundamentals of IT")
  }
  override protected def afterEach(): Unit = {
    //Delete All records
    CourseRepository.truncate().future()
  }
}
