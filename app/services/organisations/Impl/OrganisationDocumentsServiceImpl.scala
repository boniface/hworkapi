package services.organisations.Impl                                                                                                                                                                                                                       //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import com.websudos.phantom.dsl.ResultSet
import domain.organisations.OrganisationDocuments
import repositories.organisations.OrganisationDocumentsRepository
import services.Service
import services.organisations.OrganisationDocumentsService

import scala.concurrent.Future

/**
 * Created by Isiphile on 2016/10/21.
 */
class OrganisationDocumentsServiceImpl extends OrganisationDocumentsService with Service{

  override def createOrUpdate(organisationDocuments: OrganisationDocuments): Future[ResultSet] = {
    OrganisationDocumentsRepository.save(organisationDocuments)
  }

  override def deleteById(organisationId: String, organisationDocumentsId: String): Future[ResultSet] = {
    OrganisationDocumentsRepository.deleteById(organisationId,organisationDocumentsId)
  }

  override def getOrganisationDocuments(organisationId: String): Future[Seq[OrganisationDocuments]] = {
    OrganisationDocumentsRepository.getOrganisationDocuments(organisationId)
  }

  override def getFileResultById(organisationId: String, organisationDocumentsId: String): Future[Option[OrganisationDocuments]] = {
    OrganisationDocumentsRepository.getFileResultById(organisationId,organisationDocumentsId)
  }
}
