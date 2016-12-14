package repositories.common.util.training.courses

import conf.connection.DataConnection
import domain.training.courses.{CourseType, Course}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.training.courses.{CourseTypeRepository, CourseRepository}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
/**
 * Created by gavin.ackerman on 2016-12-14.
 */
class CourseTypeRepositoryTest extends FunSuite   with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    CourseTypeRepository.create.ifNotExists().future()
  }
  //CourseType(courseTypeId: String, name: String)
  test("testSaveOrUpdate") {
    val courseType = CourseType(
      "coursetypefulltime",
      "IT"
    )

    val result = Await.result(CourseTypeRepository.save(courseType), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourseType") {
    val result = Await.result(CourseTypeRepository.getCourseTypesById("course324"), 2.minutes)
    assert( result.head.name === "IT")
  }
  override protected def afterEach(): Unit = {
    //Delete All records
    CourseTypeRepository.truncate().future()
  }
}
