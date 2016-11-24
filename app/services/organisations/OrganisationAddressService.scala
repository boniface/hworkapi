package services.organisations

import com.websudos.phantom.dsl._
import io.netty.util.concurrent.Future
import domain.organisations.OrganisationAddress
import services.organisations.Impl.OrganisationAddressServiceImpl

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

