package repositories.organisations                                                                                                                                                                                                                                        //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import conf.connection.DataConnection
import domain.organisations.OrganisationDepartment
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Isiphile on 2016/12/12.
  */
class OrganisationDepartmentRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keySpace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    OrganisationDepartmentRepository.create.ifNotExists().future()
  }
    test("testSaveOrUpdate") {
      val organisationDepartment = OrganisationDepartment("101","org101","orgName","description", "active","on", new DateTime())
      val result = Await.result(OrganisationDepartmentRepository.save(organisationDepartment), 2.minutes)
      assert(result.isExhausted)
    }

  test("testGet"){
      val results = Await.result(OrganisationDepartmentRepository.getOrganisationDepartment("101"), 2.minutes)
      assert(results.head.organisationDepartmentId === "101")
    }

  test("testFindAll") {
    val result = Await.result(OrganisationDepartmentRepository.findAll, 2.minutes)
    assert( result.size > 0)
  }

  override protected def afterEach(): Unit = {
    OrganisationDepartmentRepository.truncate().future()
  }
}
