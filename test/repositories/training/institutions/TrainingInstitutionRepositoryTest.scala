package repositories.training.institutions

import conf.connection.DataConnection
import domain.training.institutions.TrainingInstitution
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Yusiry on 12/14/2016.
  */
class TrainingInstitutionRepositoryTest extends FunSuite with BeforeAndAfterEach{
  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    TrainingInstitutionRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val trainingInstitution = TrainingInstitution("ORG001", "TR001", "Name", "Email", "Status")

    val result = Await.result(TrainingInstitutionRepository.save(trainingInstitution), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetInstitution") {
    val result = Await.result(TrainingInstitutionRepository.getTrainingInstitution("ORG001"), 2.minutes)
    assert(result.head.name === "Name")
  }

  test("testGetAll") {
    val result = Await.result(TrainingInstitutionRepository.findAll, 2.minutes)
    assert( result.size > 0)
  }
  override protected def afterEach(): Unit = {
    TrainingInstitutionRepository.truncate().future()
  }
}
