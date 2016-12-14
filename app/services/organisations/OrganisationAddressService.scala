package services.organisations                                                                                                                                                          //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import com.websudos.phantom.dsl._
import domain.organisations.OrganisationAddress
import scala.concurrent.Future
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

