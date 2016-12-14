package repositories.organisations                                                                                                                                                                                                                            //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import conf.connection.DataConnection
import domain.organisations.OrganisationAddress
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Isiphile on 2016/12/12.
  */
class OrganisationAddressRepositoryTest extends FunSuite with BeforeAndAfterEach {

  implicit val keySpace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    OrganisationAddressRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val organisationAddress = OrganisationAddress("101", "@org.com", "orgAddType101", "orgLocTypeID", "addressType101", Map())
    val result = Await.result(OrganisationAddressRepository.save(organisationAddress), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGet"){
  val results = Await.result(OrganisationAddressRepository.getOrganisationAddress("101"), 2.minutes)
  assert(results.head.organisationAddressId === "101")

    test("testFindAllMedicalAid") {
      val result = Await.result(OrganisationAddressRepository.findAll, 2.minutes)
      assert( result.size > 0)
    }
}

  override protected def afterEach(): Unit = {
    OrganisationAddressRepository.truncate().future()
  }
}
