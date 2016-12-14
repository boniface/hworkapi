package services.organisations.Impl                                                                                                                                                                                                                                 //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import com.websudos.phantom.dsl._
import domain.organisations.OrganisationOffice
import repositories.organisations.OrganisationOfficeRepository
import services.Service
import services.organisations.OrganisationOfficeService

import scala.concurrent.Future

/**
 * Created by Isiphile on 2016/10/21.
 */
class OrganisationOfficeServiceImpl extends OrganisationOfficeService with Service{

 override def createOrUpdate(organisationOffice :OrganisationOffice): Future[ResultSet] = {
   OrganisationOfficeRepository.save(organisationOffice)
 }
 override def getFileResultById(organisationId: String, organisationOfficeId: String): Future[Option[OrganisationOffice]] = {
   OrganisationOfficeRepository.getFileResultById(organisationId, organisationOfficeId)
 }
 override def getOrganisationOffice(organisationId: String): Future[Seq[OrganisationOffice]] = {
   OrganisationOfficeRepository.getOrganisationOffice(organisationId)
 }
 override def deleteById(organisationId: String, organisationOfficeId: String): Future[ResultSet] = {
   OrganisationOfficeRepository.deleteById(organisationId, organisationOfficeId)
 }
}
