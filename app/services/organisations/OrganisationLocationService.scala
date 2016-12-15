package services.organisations                                                                                                                                                                                                                                                    //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import com.websudos.phantom.dsl._
import domain.organisations.OrganisationLocation
import services.organisations.Impl.OrganisationLocationServiceImpl

import scala.concurrent.Future

/**
 * Created by Isiphile on 2016/10/21.
 */
trait OrganisationLocationService {
  def createOrUpdate(organisationLocation :OrganisationLocation): Future[ResultSet]
  def getFileResultById(organisationId: String, organisationLocationId: String): Future[Option[OrganisationLocation]]
  def getOrganisationLocation(organisationId: String): Future[Seq[OrganisationLocation]]
  def deleteById(organisationId: String, organisationLocationId: String): Future[ResultSet]
}

object OrganisationLocationService{
  def apply: OrganisationLocationService = new OrganisationLocationServiceImpl()
}
