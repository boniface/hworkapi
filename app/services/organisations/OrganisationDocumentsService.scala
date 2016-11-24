package services.organisations

import com.websudos.phantom.dsl._
import domain.organisations.OrganisationDocuments
import services.organisations.Impl.OrganisationDocumentsServiceImpl

import scala.concurrent.Future

/**
 * Created by Isiphile on 2016/10/21.
 */
trait OrganisationDocumentsService {
  def createOrUpdate(organisationDocuments :OrganisationDocuments): Future[ResultSet]
  def getFileResultById(organisationId: String, organisationDocumentsId: String): Future[Option[OrganisationDocuments]]
  def getOrganisationDocuments(organisationId: String): Future[Seq[OrganisationDocuments]]
  def deleteById(organisationId: String, organisationDocumentsId: String): Future[ResultSet]
}

object OrganisationDocumentsService{
  def apply: OrganisationDocumentsService = new OrganisationDocumentsServiceImpl()
}
