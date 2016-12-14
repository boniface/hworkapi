package repositories.common.util.training.schedules

import conf.connection.DataConnection
import domain.training.courses.Course
import domain.training.schedules.CourseFunding
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.training.courses.CourseRepository
import repositories.training.schedules.CourseFundingRepository
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
/**
 * Created by gavin.ackerman on 2016-12-14.
 */
class CourseFundingRepositoryTest extends FunSuite   with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    CourseFundingRepository.create.ifNotExists().future()
  }
  //CourseFunding(scheduledCourseId: String,
  //  fundingSourcesId:String,
    //amount:BigDecimal,
  //  currencyId:String)
  test("testSaveOrUpdate") {
    val courseFunding = CourseFunding(
      "sched63",
      "fund534",
      54,
      "currency45"
    )

    val result = Await.result(CourseFundingRepository.save(courseFunding), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourseFunding") {
    val result = Await.result(CourseFundingRepository.getCourseFundingById("course324","fund534"), 2.minutes)
    assert( result.head.amount === 54)
  }
  override protected def afterEach(): Unit = {
    //Delete All records
    CourseFundingRepository.truncate().future()
  }
}
