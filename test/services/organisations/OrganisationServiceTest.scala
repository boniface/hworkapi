package services.organisations

import domain.organisations.Organisation
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Isiphile on 2016-12-09.
  */
class OrganisationServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val organisation = Organisation("101", "organisation", Map(), "adminattached", new DateTime(), "active")
    val result = Await.result(OrganisationService.apply.createOrUpdate(organisation), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetOrganisation") {
    val result = Await.result(OrganisationService.apply.getOrganisation("101"), 2.minutes)
    assert(result.head.organisationId === "101")
  }
}
