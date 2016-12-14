package repositories.training.institutions

import conf.connection.DataConnection
import domain.training.institutions.TrainingInstructor
import org.joda.time.DateTime
import scala.concurrent.duration._
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await

/**
  * Created by Yusiry on 12/14/2016.
  */
class TrainingInstructorRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    TrainingInstructorRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val trainingInstructor = TrainingInstructor("ORG01", "TR01", "Title", "Jack", "Jill", "ND", "IT")

    val result = Await.result(TrainingInstructorRepository.save(trainingInstructor), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetInstructor") {
    val result = Await.result(TrainingInstructorRepository.getTrainingInstructor("ORG01"), 2.minutes)
    assert(result.head.firstName === "Jack")
  }

  test("testFindAllInstructors") {
    val result = Await.result(TrainingInstructorRepository.findAll, 2.minutes)
    assert( result.size > 0)
  }
  override protected def afterEach(): Unit = {
    TrainingInstructorRepository.truncate().future()
  }
}
