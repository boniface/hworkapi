package repositories.common.util.training.schedules

import conf.connection.DataConnection
import domain.training.schedules.{CourseParticipants, CourseInstructors}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.training.schedules.{CourseParticipantsRepository, CourseInstructorsRepository}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
/**
 * Created by gavin.ackerman on 2016-12-14.
 */
class CourseParticipantsRepositoryTest extends FunSuite   with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    CourseParticipantsRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val courseParticipants = CourseParticipants(
      "sched63",
      "user534"

    )

    val result = Await.result(CourseParticipantsRepository.save(courseParticipants), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourseParticipants") {
    val result = Await.result(CourseParticipantsRepository.getCourseParticipantsById("sched63","user534"), 2.minutes)
    assert( result.head.scheduledCourseId === "sched63")
  }
  override protected def afterEach(): Unit = {
    //Delete All records
    CourseParticipantsRepository.truncate().future()
  }
}
