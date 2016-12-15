package repositories.common.util.training.schedules

import conf.connection.DataConnection
import domain.training.schedules.{CourseRating, CourseParticipants}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.training.schedules.{CourseRatingRepository, CourseParticipantsRepository}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
/**
 * Created by gavin.ackerman on 2016-12-14.
 */
class CourseRatingRepositoryTest extends FunSuite   with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    CourseRatingRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val courseRating = CourseRating(
      "org63",
      "sched534",
       34,
      "blah"
    )

    val result = Await.result(CourseRatingRepository.save(courseRating), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourseRating") {
    val result = Await.result(CourseRatingRepository.getCourseRatingById("org63","sched534"), 2.minutes)
    assert( result.head.rating === 34)
  }
  override protected def afterEach(): Unit = {
    //Delete All records
    CourseRatingRepository.truncate().future()
  }
}
