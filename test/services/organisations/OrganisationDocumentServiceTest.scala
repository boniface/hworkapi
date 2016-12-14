package services.organisations                                                                                                                                                                                                                                                                    //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import domain.organisations.OrganisationDocuments
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Isiphile on 2016-12-09.
  */
class OrganisationDocumentServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val organisationDocuments = OrganisationDocuments("ORG100", "orgDocumentsID","description","url", "mime", new DateTime(), Set(), "active")
    val result = Await.result(OrganisationDocumentsService.apply.createOrUpdate(organisationDocuments), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetOrganisationDocument") {
    val result = Await.result(OrganisationDocumentsService.apply.getOrganisationDocuments("ORG100"), 2.minutes)
    assert(result.head.organisationDocumentsId === "ORG100")
  }
}
