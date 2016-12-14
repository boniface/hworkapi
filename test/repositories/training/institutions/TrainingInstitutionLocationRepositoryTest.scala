package repositories.training.institutions

import conf.connection.DataConnection
import domain.training.institutions.TrainingInstitutionLocation
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Yusiry on 12/14/2016.
  */
class TrainingInstitutionLocationRepositoryTest extends FunSuite with BeforeAndAfterEach {

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    TrainingInstitutionLocationRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val trainingInstitutionLocation = TrainingInstitutionLocation("ORG001", "LOC001",  "Name", "TYP001", "Code","LAT", "LON", "State", new DateTime(2016, 11, 9, 12, 0, 0, 0))

    val result = Await.result(TrainingInstitutionLocationRepository.save(trainingInstitutionLocation), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetLocation") {
    val result = Await.result(TrainingInstitutionLocationRepository.getTrainingInstitutionLocation("ORG001"), 2.minutes)
    assert(result.head.code === "Code")
  }

  test("testFindAllLocations") {
    val result = Await.result(TrainingInstitutionLocationRepository.findAll, 2.minutes)
    assert( result.size > 0)
  }
  override protected def afterEach(): Unit = {
    TrainingInstitutionLocationRepository.truncate().future()
  }
}
