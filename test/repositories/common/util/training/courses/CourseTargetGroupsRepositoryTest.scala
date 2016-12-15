package repositories.common.util.training.courses

import conf.connection.DataConnection
import domain.training.courses.{CourseTargetGroups, Course}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.training.courses.{CourseTargetGroupsRepository, CourseRepository}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
/**
 * Created by gavin.ackerman on 2016-12-14.
 */
class CourseTargetGroupsRepositoryTest extends FunSuite   with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    CourseTargetGroupsRepository.create.ifNotExists().future()
  }
  //(organisationId:String,courseId:String,targetGroupId:String)
  test("testSaveOrUpdate") {
    val courseTargetGroups = CourseTargetGroups(
      "org53",
      "course324",
      "targetgroup532"
    )

    val result = Await.result(CourseTargetGroupsRepository.save(courseTargetGroups), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCourseTargetGroups") {
    val result = Await.result(CourseTargetGroupsRepository.getCourseTargetGroupsById("org53","course324","targetgroup532"), 2.minutes)
    assert( result.head.targetGroupId === "targetgroup532")
  }
  override protected def afterEach(): Unit = {
    //Delete All records
    CourseTargetGroupsRepository.truncate().future()
  }
}
