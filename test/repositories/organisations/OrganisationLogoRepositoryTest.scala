package repositories.organisations                                                                                                                                                                                                                        //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import conf.connection.DataConnection
import domain.organisations.OrganisationLogo
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Isiphile on 2016/12/12.
  */
class OrganisationLogoRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keySpace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    OrganisationLogoRepository.create.ifNotExists().future()
  }
    test("testSaveOrUpdate") {
      val organisationLogo = OrganisationLogo("123","org101","org.com", Option("size"), "logo", "mime", new DateTime())
      val result = Await.result(OrganisationLogoRepository.save(organisationLogo), 2.minutes)
      assert(result.isExhausted)
    }

  test("testGet"){
      val results = Await.result(OrganisationLogoRepository.getOrganisationLogo("123"), 2.minutes)
      assert(results.head.organisationLogoId === "123")
    }

  test("testFindAll") {
    val result = Await.result(OrganisationLogoRepository.findAll, 2.minutes)
    assert( result.size > 0)
  }

  override protected def afterEach(): Unit = {
    OrganisationLogoRepository.truncate().future()
  }
}
