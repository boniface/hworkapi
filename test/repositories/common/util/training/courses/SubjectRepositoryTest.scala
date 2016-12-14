package repositories.common.util.training.courses

import conf.connection.DataConnection
import domain.training.courses.{Subject, Course}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.training.courses.{SubjectRepository, CourseRepository}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
/**
 * Created by gavin.ackerman on 2016-12-14.
 */
class SubjectRepositoryTest extends FunSuite   with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    SubjectRepository.create.ifNotExists().future()
  }
 // Subject(subjectId: String, topic: String, subjectCode: String, description: String, credit: Int)
  test("testSaveOrUpdate") {
    val subject = Subject(
      "sub53",
      "design",
      "subco343",
      "strucutres",
      542
    )

    val result = Await.result(SubjectRepository.save(subject), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetSubject") {
    val result = Await.result(SubjectRepository.getSubjectById("sub53"), 2.minutes)
    assert( result.head.credit === 542)
  }
  override protected def afterEach(): Unit = {
    //Delete All records
    SubjectRepository.truncate().future()
  }
}
