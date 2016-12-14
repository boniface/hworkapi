package repositories.common.util.training.courses

import conf.connection.DataConnection
import domain.training.courses.{CourseSubjects, Course}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.training.courses.{CourseSubjectsRepository, CourseRepository}
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by gavin.ackerman on 2016-12-14.
 */
class CourseSubjectsRepositoryTest extends FunSuite   with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    CourseSubjectsRepository.create.ifNotExists().future()
  }
  //CourseSubjects(organisationId:String,courseId:String,subjectId:String)
  test("testSaveOrUpdate") {
    val courseSubjects = CourseSubjects(
      "org53",
      "course324",
      "sub443"
    )

    val result = Await.result(CourseSubjectsRepository.save(courseSubjects), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourseSubjects") {
    val result = Await.result(CourseSubjectsRepository.getCourseSubjectsById("org53","course324","sub443"), 2.minutes)
    assert( result.head.subjectId === "sub443")
  }
  override protected def afterEach(): Unit = {
    //Delete All records
    CourseSubjectsRepository.truncate().future()
  }
}
