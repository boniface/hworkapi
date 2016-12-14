package repositories.organisations                                                                                                                                                                                                                        //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import conf.connection.DataConnection
import domain.organisations.OrganisationContact
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Isiphile on 2016/12/12.
  */
class OrganisationContactRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keySpace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    OrganisationContactRepository.create.ifNotExists().future()
  }
    test("testSaveOrUpdate") {
      val organisationContact = OrganisationContact("123","email","078org","tell", Map())
      val result = Await.result(OrganisationContactRepository.save(organisationContact), 2.minutes)
      assert(result.isExhausted)
    }

  test("testGet"){
      val results = Await.result(OrganisationContactRepository.getOrganisationContact("101"), 2.minutes)
      assert(results.head.organisationContactId === "101")
    }

  test("testFindAll") {
    val result = Await.result(OrganisationContactRepository.findAll, 2.minutes)
    assert( result.size > 0)
  }

  override protected def afterEach(): Unit = {
    OrganisationContactRepository.truncate().future()
  }
}
