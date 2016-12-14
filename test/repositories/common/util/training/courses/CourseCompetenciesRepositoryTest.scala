package repositories.common.util.training.courses

import conf.connection.DataConnection
import domain.training.courses.{CourseCompetencies, Course}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.training.courses.{CourseCompetenciesRepository, CourseRepository}
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by gavin.ackerman on 2016-12-14.
 */
class CourseCompetenciesRepositoryTest extends FunSuite   with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    CourseCompetenciesRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val courseCompetencies = CourseCompetencies(
      "org53",
      "course324",
      "comp443"

    )

    val result = Await.result(CourseCompetenciesRepository.save(courseCompetencies), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourse") {
    val result = Await.result(CourseCompetenciesRepository.getCourseCompetenciesById("org53","course324"), 2.minutes)
    assert( result.head.courseId === "course324")
  }
  override protected def afterEach(): Unit = {
    //Delete All records
    CourseCompetenciesRepository.truncate().future()
  }

}
