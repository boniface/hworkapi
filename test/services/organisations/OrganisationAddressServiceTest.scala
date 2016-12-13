package services.organisations

import domain.organisations.OrganisationAddress
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Isiphile on 2016-12-09.
  */
class OrganisationAddressServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val organisationAddress = OrganisationAddress("ORG100", "email", "addressID", "orgLocID", "addressTypeID","details")
    val result = Await.result(OrganisationAddressService.apply.createOrUpdate(organisationAddress), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetOrganisationAddress") {
    val result = Await.result(OrganisationAddressService.apply.getOrganisationAddress("ORG100"), 2.minutes)
    assert(result.head.addressTypeId === "addressTypeID")
  }
}
