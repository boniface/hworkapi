package services.organisations.Impl                                                                                                                                                                                                                           //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import com.websudos.phantom.dsl.ResultSet
import domain.organisations.OrganisationAddress
import repositories.organisations.OrganisationAddressRepository
import services.organisations.OrganisationAddressService
import services.Service
import scala.concurrent.Future
/**
 * Created by Isiphile on 2016/10/21.
 */
class OrganisationAddressServiceImpl extends  OrganisationAddressService with  Service{
 override def createOrUpdate(organisationAddress : OrganisationAddress):Future[ResultSet] = {
   OrganisationAddressRepository.save(organisationAddress)
 }

  override def getOrganisationAddress(organisationAddress: String): Future[Seq[OrganisationAddress]] ={
    OrganisationAddressRepository.getOrganisationAddress(organisationAddress)
  }

  override def deleteById(organisationAddress: String, organisationAddressId: String): Future[ResultSet] = {
    OrganisationAddressRepository.deleteById(organisationAddress,organisationAddressId)
  }
}
