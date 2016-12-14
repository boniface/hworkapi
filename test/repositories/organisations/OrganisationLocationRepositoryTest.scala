package repositories.organisations                                                                                                                                                                                                                //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import conf.connection.DataConnection
import domain.organisations.OrganisationLocation
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Isiphile on 2016/12/12.
  */
class OrganisationLocationRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keySpace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    OrganisationLocationRepository.create.ifNotExists().future()
  }
    test("testSaveOrUpdate") {
      val organisationLocation = OrganisationLocation("123","orgLoc101","orgName",
                                                      "orgLocType101", "101","latitude",
                                                      "longitude", "parent101", "active", new DateTime())
      val result = Await.result(OrganisationLocationRepository.save(organisationLocation), 2.minutes)
      assert(result.isExhausted)
    }

  test("testGet"){
      val results = Await.result(OrganisationLocationRepository.getOrganisationLocation("123"), 2.minutes)
      assert(results.head.organisationLocationId === "123")
    }

  test("testFindAll") {
    val result = Await.result(OrganisationLocationRepository.findAll, 2.minutes)
    assert( result.size > 0)
  }

  override protected def afterEach(): Unit = {
    OrganisationLocationRepository.truncate().future()
  }
}
