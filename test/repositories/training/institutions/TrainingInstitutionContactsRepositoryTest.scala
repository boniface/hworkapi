package repositories.training.institutions

import conf.connection.DataConnection
import domain.training.institutions.TrainingInstitutionContacts
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Yusiry on 12/14/2016.
  */
class TrainingInstitutionContactsRepositoryTest extends FunSuite with BeforeAndAfterEach {
  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    TrainingInstitutionContactsRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val contacts = TrainingInstitutionContacts("ORG001", "TR001", "CON001", null)

    val result = Await.result(TrainingInstitutionContactsRepository.save(contacts), 2.minutes)
    assert(result.isExhausted)
  }


  test("testGetContacts") {
    val result = Await.result(TrainingInstitutionContactsRepository.getTraningInstitutionContacts("ORG100"), 2.minutes)
    assert(result.size > 0)
  }

  override protected def afterEach(): Unit = {
    TrainingInstitutionContactsRepository.truncate().future()
  }
}
