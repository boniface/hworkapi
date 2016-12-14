package repositories.organisations                                                                                                                                                                                                                                                        //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import conf.connection.DataConnection
import domain.organisations.OrganisationOffice
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Isiphile on 2016/12/12.
  */
class OrganisationOfficeRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keySpace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    OrganisationLogoRepository.create.ifNotExists().future()
  }
    test("testSaveOrUpdate") {
      val organisationOffice = OrganisationOffice("123","orgOffice101","orgName", "organisation", "active", "office101", "on", new DateTime())
      val result = Await.result(OrganisationOfficeRepository.save(organisationOffice), 2.minutes)
      assert(result.isExhausted)
    }

  test("testGet"){
      val results = Await.result(OrganisationOfficeRepository.getOrganisationOffice("123"), 2.minutes)
      assert(results.head.organisationOfficeId === "123")
    }

  test("testFindAll") {
    val result = Await.result(OrganisationOfficeRepository.findAll, 2.minutes)
    assert( result.size > 0)
  }

  override protected def afterEach(): Unit = {
    OrganisationLogoRepository.truncate().future()
  }
}
