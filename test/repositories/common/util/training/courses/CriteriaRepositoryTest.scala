package repositories.common.util.training.courses

import conf.connection.DataConnection
import domain.training.courses.{Criteria, Course}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.training.courses.{CriteriaRepository, CourseRepository}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
/**
 * Created by gavin.ackerman on 2016-12-14.
 */
class CriteriaRepositoryTest extends FunSuite   with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    CriteriaRepository.create.ifNotExists().future()
  }
  //Criteria(criteriaId:String, name:String)
  test("testSaveOrUpdate") {
    val criteria = Criteria(
      "criteria54",
      "course324"
    )

    val result = Await.result(CriteriaRepository.save(criteria), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCriteria") {
    val result = Await.result(CriteriaRepository.getCriteriaById("criteria54"), 2.minutes)
    assert( result.head.name === "course324")
  }
  override protected def afterEach(): Unit = {
    //Delete All records
    CriteriaRepository.truncate().future()
  }
}
