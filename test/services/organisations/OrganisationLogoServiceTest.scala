package services.organisations

import domain.organisations.OrganisationLogo
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Isiphile on 2016-12-09.
  */
abstract class OrganisationLogoServiceTest extends FunSuite {

  val size: Option[String]
  test("testSaveOrUpdate") {
    val organisationLogo = OrganisationLogo("ORG100", "orgLogoID", "url",size, "description","mime", new DateTime())
    val result = Await.result(OrganisationLogoService.apply.createOrUpdate(organisationLogo), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetLogo") {
    val result = Await.result(OrganisationLogoService.apply.getOrganisationLogo("ORG100"), 2.minutes)
    assert(result.head.organisationLogoId === "ORG100")
  }
}
