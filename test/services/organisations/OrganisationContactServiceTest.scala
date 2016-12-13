package services.organisations

import domain.organisations.OrganisationContact
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Isiphile on 2016-12-09.
  */
class OrganisationContactServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val organisationContact = OrganisationContact("ORG100", "Email", "contactID","contactTypeID", "details")
    val result = Await.result(OrganisationContactService.apply.createOrUpdate(organisationContact), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetBenefit") {
    val result = Await.result(OrganisationContactService.apply.getOrganisationContact("ORG100"), 2.minutes)
    assert(result.head.organisationId === "ORG100")
  }
}
