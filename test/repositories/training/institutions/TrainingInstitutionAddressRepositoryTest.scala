package repositories.training.institutions


import conf.connection.DataConnection
import domain.training.institutions.TrainingInstitutionAddress
import org.joda.time.DateTime
import scala.concurrent.duration._
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await

/**
  * Created by Yusiry on 12/14/2016.
  */
class TrainingInstitutionAddressRepositoryTest extends FunSuite with BeforeAndAfterEach {

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    TrainingInstitutionAddressRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val address = TrainingInstitutionAddress("ORG001", "ADD0001", "LOC001", "TY001", null)

    val result = Await.result(TrainingInstitutionAddressRepository.save(address), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetAddress") {
    val result = Await.result(TrainingInstitutionAddressRepository.getTrainingInstitutionAddress("ORG100"), 2.minutes)
    assert(result.head.addressTypeId === "ADD0001")
  }

  override protected def afterEach(): Unit = {
    TrainingInstitutionAddressRepository.truncate().future()
  }
}
