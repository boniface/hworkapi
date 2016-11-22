package services.organisations.Impl

import com.websudos.phantom.dsl._
import domain.organisations.OrganisationLocation
import repositories.organisations.OrganisationLocationRepository
import services.Service
import services.organisations.OrganisationLocationService

import scala.concurrent.Future

/**
 * Created by Isiphile on 2016/10/21.
 */
class OrganisationLocationServiceImpl extends OrganisationLocationService with Service{
 override def createOrUpdate(organisationLocation :OrganisationLocation): Future[ResultSet] = {
   OrganisationLocationRepository.save(organisationLocation)
 }
 override def getFileResultById(organisationId: String, organisationLocationId: String): Future[Option[OrganisationLocation]] = {
   OrganisationLocationRepository.getFileResultById(organisationId, organisationLocationId)
 }
 override def getOrganisationLocation(organisationId: String): Future[Seq[OrganisationLocation]] = {
   OrganisationLocationRepository.getOrganisationLocation(organisationId)
 }
 override def deleteById(organisationId: String, organisationLocationId: String): Future[ResultSet] = {
   OrganisationLocationRepository.deleteById(organisationId, organisationLocationId)
 }
}
