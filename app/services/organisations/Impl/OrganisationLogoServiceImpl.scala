package services.organisations.Impl                                                                                                                                                                                                                           //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import com.websudos.phantom.dsl._
import domain.organisations.OrganisationLogo
import repositories.organisations.OrganisationLogoRepository
import services.Service
import services.organisations.OrganisationLogoService

import scala.concurrent.Future

/**
 * Created by Isiphile on 2016/10/21.
 */
class OrganisationLogoServiceImpl extends OrganisationLogoService with  Service{
  override  def createOrUpdate(organisationLogo :OrganisationLogo): Future[ResultSet] = {
    OrganisationLogoRepository.save(organisationLogo)
  }
 override def getFileResultById(organisationId: String, organisationLogoId: String): Future[Option[OrganisationLogo]] = {
   OrganisationLogoRepository.getFileResultById(organisationId, organisationLogoId)
 }
 override def getOrganisationLogo(organisationId: String): Future[Seq[OrganisationLogo]] = {
  OrganisationLogoRepository.getOrganisationLogo(organisationId)
 }
 override def deleteById(organisationId: String, organisationLogoId: String): Future[ResultSet] = {
   OrganisationLogoRepository.deleteById(organisationId, organisationLogoId)
 }

}
