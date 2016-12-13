package services.organisations

import domain.organisations.OrganisationLocation
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Isiphile on 2016-12-09.
  */
class OrganisationLocationServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val organisationLocation = OrganisationLocation("ORG100", "orgLocationID", "orgName", "locTypeID", "code", "latitude", "longitude","parentID","state", new DateTime())
    val result = Await.result(OrganisationLocationService.apply.createOrUpdate(organisationLocation), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetOrganisationAddress") {
    val result = Await.result(OrganisationLocationService.apply.getOrganisationLocation("ORG100"), 2.minutes)
    assert(result.head.organisationId === "ORG100")
  }
}
