package repositories.common.util.training.courses

import conf.connection.DataConnection
import domain.training.courses.{CourseCategory, Course}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.training.courses.{CourseCategoryRepository, CourseRepository}
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by gavin.ackerman on 2016-12-14.
 */
class CourseCategoryRepositoryTest extends FunSuite   with BeforeAndAfterEach {

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    CourseCategoryRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val courseCategory = CourseCategory(
      "org13",
      "coursecat23",
      "design"

    )

    val result = Await.result(CourseCategoryRepository.save(courseCategory), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourseCategory") {
    val result = Await.result(CourseCategoryRepository.getCourseCategoryById("org13","coursecat23"), 2.minutes)
    assert(result.head.name === "design")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    CourseCategoryRepository.truncate().future()
  }
}