package repositories.organisations                                                                                                                                                                                                                                                            //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import conf.connection.DataConnection
import domain.organisations.OrganisationDocuments
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Isiphile on 2016/12/12.
  */
class OrganisationDocumentsRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keySpace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    OrganisationDocumentsRepository.create.ifNotExists().future()
  }
    test("testSaveOrUpdate") {
      val organisationDocuments = OrganisationDocuments("123","orgDoc101", "description","www.org.com", "mime", new DateTime(), Set(), "active")
      val result = Await.result(OrganisationDocumentsRepository.save(organisationDocuments), 2.minutes)
      assert(result.isExhausted)
    }

  test("testGet"){
      val results = Await.result(OrganisationDocumentsRepository.getOrganisationDocuments("123"), 2.minutes)
      assert(results.head.organisationDocumentsId === "123")
    }

  test("testFindAll") {
    val result = Await.result(OrganisationDocumentsRepository.findAll, 2.minutes)
    assert( result.size > 0)
  }

  override protected def afterEach(): Unit = {
    OrganisationDocumentsRepository.truncate().future()
  }
}
