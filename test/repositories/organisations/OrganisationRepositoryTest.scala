package repositories.organisations                                                                                                                                                                                                                                          //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import conf.connection.DataConnection
import domain.organisations.Organisation
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Isiphile on 2016/12/12.
  */
class OrganisationRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keySpace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    OrganisationRepository.create.ifNotExists().future()
  }
    test("testSaveOrUpdate") {
      val organisation = Organisation("101", "organisation", Map(), "adminattached", new DateTime(), "active")
      val result = Await.result(OrganisationRepository.save(organisation), 2.minutes)
      assert(result.isExhausted)
    }

  test("testGet"){
      val results = Await.result(OrganisationRepository.getOrganisation("101"), 2.minutes)
      assert(results.head.organisationId === "101")
    }

  test("testFindAll") {
    val result = Await.result(OrganisationRepository.findAll, 2.minutes)
    assert( result.size > 0)
  }

  override protected def afterEach(): Unit = {
    OrganisationRepository.truncate().future()
  }
}
