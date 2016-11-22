package services.organisations

import com.websudos.phantom.dsl._
import domain.organisations.OrganisationLogo
import services.organisations.Impl.OrganisationLogoServiceImpl

import scala.concurrent.Future

/**
 * Created by Isiphile on 2016/10/21.
 */
trait OrganisationLogoService {
  def createOrUpdate(organisationLogo :OrganisationLogo): Future[ResultSet]
  def getFileResultById(organisationId: String, organisationLogoId: String): Future[Option[OrganisationLogo]]
  def getOrganisationLogo(organisationId: String): Future[Seq[OrganisationLogo]]
  def deleteById(organisationId: String, organisationLogoId: String): Future[ResultSet]
}
object OrganisationLogoService{
  def apply: OrganisationLogoService = new OrganisationLogoServiceImpl()
}
