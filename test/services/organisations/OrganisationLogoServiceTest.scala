package services.organisations

import domain.organisations.OrganisationLogo
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Isiphile on 2016-12-09.
  */
class OrganisationLogoServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val organisationLogo = OrganisationLogo("ORG100", "orgLogoID", "url","size", "description","mime", new DateTime())
    val result = Await.result(OrganisationLogoService.apply.createOrUpdate(organisationLogo), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetBenefit") {
    val result = Await.result(OrganisationLogoService.apply.getOrganisationLogo("ORG100"), 2.minutes)
    assert(result.head.organisationId === "ORG100")
  }
}
