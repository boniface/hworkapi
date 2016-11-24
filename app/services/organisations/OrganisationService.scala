package services.organisations

import com.websudos.phantom.dsl._
import domain.organisations.Organisation
import services.organisations.Impl.OrganisationServiceImpl

import scala.concurrent.Future

/**
 * Created by Isiphile on 2016/10/21.
 */
trait OrganisationService {
  def createOrUpdate(organisation: Organisation): Future[ResultSet]
  def findById(organisationId: String):Future[Option[Organisation]]
  def getOrganisation(organisationId: String): Future[Seq[Organisation]]
  def deleteById(organisationId:String): Future[ResultSet]
}

object OrganisationService{
  def apply: OrganisationService = new OrganisationServiceImpl()
}
