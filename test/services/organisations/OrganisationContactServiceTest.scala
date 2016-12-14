package services.organisations                                                                                                                                                                                                                                //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import domain.organisations.OrganisationContact
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Isiphile on 2016-12-09.
  */
class OrganisationContactServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val organisationContact = OrganisationContact("ORG100", "Email", "contactID","contactTypeID", Map())
    val result = Await.result(OrganisationContactService.apply.createOrUpdate(organisationContact), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGet") {
    val result = Await.result(OrganisationContactService.apply.getOrganisationContact("ORG100"), 2.minutes)
    assert(result.head.organisationContactId === "ORG100")
  }
}
