package services.organisations

import com.websudos.phantom.dsl._
import domain.organisations.OrganisationAddress
import services.organisations.Impl.OrganisationAddressServiceImpl
import scala.concurrent.Future

/**
 * Created by Isiphile on 2016/10/21.
 */
trait OrganisationAddressService {
  def createOrUpdate(organisationAddress: OrganisationAddress): Future[ResultSet]

  def getOrganisationAddress(organisationAddress: String): Future[Seq[OrganisationAddress]]

  def deleteById(organisationAddress: String, organisationAddressId: String): Future[ResultSet]
}
object OrganisationAddressService{
    def apply: OrganisationAddressService = new OrganisationAddressServiceImpl()
}

