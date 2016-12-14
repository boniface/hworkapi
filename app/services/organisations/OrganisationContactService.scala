package services.organisations                                                                                                                                                                                           //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import com.websudos.phantom.dsl._
import domain.organisations.OrganisationContact
import services.organisations.Impl.OrganisationContactServiceImp

import scala.concurrent.Future

/**
 * Created by Isiphile on 2016/10/21.
 */
trait OrganisationContactService {

  def createOrUpdate(organisationContact :OrganisationContact): Future[ResultSet]
  def getFileResultById(organisationId: String, organisationContactId: String): Future[Option[OrganisationContact]]
  def getOrganisationContact(organisationId: String): Future[Seq[OrganisationContact]]
  def deleteById(organisationId: String, organisationContactId: String): Future[ResultSet]
}

object  OrganisationContactService {
  def apply: OrganisationContactService = new OrganisationContactServiceImp()
}
