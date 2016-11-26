package services.users.impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.UserAddress
import repositories.users.UserAddressRepository
import services.Service
import services.users.UserAddressService

import scala.concurrent.Future
/**
  * Created by Lonwabo on 10/31/2016.
  */
class UserAddressServiceImpl extends UserAddressService with Service{
  override def createOrupdate(userAddress: UserAddress): Future[ResultSet] = {
    UserAddressRepository.save(userAddress)
  }
  override def getUserAddressById(organisationId: String, userId: String, userAddressid: String): Future[Option[UserAddress]] = {
    UserAddressRepository.findById(organisationId,userId,userAddressid)
  }

  override def getAllUserAddress(person: String): Future[Seq[UserAddress]] = {
    UserAddressRepository.findAll
  }
}
