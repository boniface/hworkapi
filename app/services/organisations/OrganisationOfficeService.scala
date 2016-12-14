package services.organisations                                                                                                                                                                                                                                              //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import com.websudos.phantom.dsl._
import domain.organisations.OrganisationOffice
import services.organisations.Impl.OrganisationOfficeServiceImpl

import scala.concurrent.Future

/**
 * Created by Isiphile on 2016/10/21.
 */
trait OrganisationOfficeService {
  def createOrUpdate(organisationOffice :OrganisationOffice): Future[ResultSet]
  def getFileResultById(organisationId: String, organisationOfficeId: String): Future[Option[OrganisationOffice]]
  def getOrganisationOffice(organisationId: String): Future[Seq[OrganisationOffice]]
  def deleteById(organisationId: String, organisationOfficeId: String): Future[ResultSet]
}

object OrganisationOfficeService{
  def apply: OrganisationOfficeService = new OrganisationOfficeServiceImpl()
}
