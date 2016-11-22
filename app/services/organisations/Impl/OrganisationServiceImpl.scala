package services.organisations.Impl

import com.websudos.phantom.dsl._
import domain.organisations.Organisation
import repositories.organisations.OrganisationRepository
import services.Service
import services.organisations.OrganisationService

import scala.concurrent.Future

/**
 * Created by Isiphile on 2016/10/21.
 */
class OrganisationServiceImpl extends OrganisationService with Service{

  override def createOrUpdate(organisation: Organisation): Future[ResultSet] = {
    OrganisationRepository.save(organisation)
  }
 override def findById(organisationId: String):Future[Option[Organisation]] = {
   OrganisationRepository.findById(organisationId)
 }
 override def getOrganisation(organisationId: String): Future[Seq[Organisation]] = {
   OrganisationRepository.getOrganisation(organisationId)
 }
 override def deleteById(organisationId:String): Future[ResultSet] = {
   OrganisationRepository.deleteById(organisationId)
 }
}
