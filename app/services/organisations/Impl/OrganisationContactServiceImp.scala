package services.organisations.Impl                                                                                                                                                                                                                     //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import com.websudos.phantom.dsl._
import domain.organisations.OrganisationContact
import repositories.organisations.OrganisationContactRepository
import services.Service
import services.organisations.OrganisationContactService

import scala.concurrent.Future

/**
 * Created by Isiphile on 2016/10/21.
 */
class OrganisationContactServiceImp extends OrganisationContactService with Service{

 override def createOrUpdate(organisationContact :OrganisationContact): Future[ResultSet] = {
    OrganisationContactRepository.save(organisationContact)
  }
  override def getFileResultById(organisationId: String, organisationContactId: String): Future[Option[OrganisationContact]] ={
  OrganisationContactRepository.getFileResultById(organisationId,organisationContactId)
 }
 override def getOrganisationContact(organisationId: String): Future[Seq[OrganisationContact]] = {
   OrganisationContactRepository.getOrganisationContact(organisationId)
 }
 override def deleteById(organisationId: String, organisationContactId: String): Future[ResultSet] = {
   OrganisationContactRepository.deleteById(organisationId, organisationContactId)
 }
}
