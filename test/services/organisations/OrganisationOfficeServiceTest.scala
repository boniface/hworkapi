package services.organisations

import domain.organisations.OrganisationOffice
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Isiphile on 2016-12-09.
  */
class OrganisationOfficeServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val organisationOffice = OrganisationOffice("123","orgOffice101","orgName", "organisation", "active", "office101", "on", new DateTime())
    val result = Await.result(OrganisationOfficeService.apply.createOrUpdate(organisationOffice), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetOrganisationOffice") {
    val result = Await.result(OrganisationOfficeService.apply.getOrganisationOffice("123"), 2.minutes)
    assert(result.head.organisationOfficeId === "123")
  }
}
