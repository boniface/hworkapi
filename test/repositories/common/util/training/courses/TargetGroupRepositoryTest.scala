package repositories.common.util.training.courses

import conf.connection.DataConnection
import domain.training.courses.{TargetGroup, Course}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.training.courses.{TargetGroupRepository, CourseRepository}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
/**
 * Created by gavin.ackerman on 2016-12-14.
 */
class TargetGroupRepositoryTest extends FunSuite   with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    TargetGroupRepository.create.ifNotExists().future()
  }
  //TargetGroup(targetGroupId: String, name: String)
  test("testSaveOrUpdate") {
    val targetGroup = TargetGroup(
      "target53",
      "high"
    )

    val result = Await.result(TargetGroupRepository.save(targetGroup), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetTargetGroup") {
    val result = Await.result(TargetGroupRepository.getTargetGroupById("target53"), 2.minutes)
    assert( result.head.name === "high")
  }
  override protected def afterEach(): Unit = {
    //Delete All records
    TargetGroupRepository.truncate().future()
  }

}
