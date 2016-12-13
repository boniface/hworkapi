package services.organisations

import domain.organisations.Organisation
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Isiphile on 2016-12-09.
  */
class OrganisationOfficeServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val organisation = Organisation("ORG100", "BN100", "details", "adminattached", new DateTime())
    val result = Await.result(OrganisationService.apply.createOrUpdate(organisation), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetOrganisation") {
    val result = Await.result(OrganisationService.apply.getOrganisation("ORG100"), 2.minutes)
    assert(result.head.name === "Benefit Name")
  }
}
