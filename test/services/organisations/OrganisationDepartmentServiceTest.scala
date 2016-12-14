package services.organisations                                                                                                                                                                                                                                                        //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import domain.organisations.OrganisationDepartment
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Isiphile on 2016-12-09.
  */
class OrganisationDepartmentServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val organisationDepartment = OrganisationDepartment("ORG100", "orgDepartmentID", "orgName","description","active", "active", new DateTime())
    val result = Await.result(OrganisationDepartmentService.apply.createOrUpdate(organisationDepartment), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetOrganisationDepartment") {
    val result = Await.result(OrganisationDepartmentService.apply.getOrganisationDepartment("ORG100"), 2.minutes)
    assert(result.head.organisationDepartmentId === "ORG100")
  }
}
