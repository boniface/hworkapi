package services.users

import com.websudos.phantom.dsl._
import domain.users.UserAddress
import services.users.impl.UserAddressServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait UserAddressService {
  def createOrupdate(userAddress : UserAddress):Future[ResultSet]

  def getUserAddressById(organisationId : String,userId: String, userAddressid: String): Future[Option[UserAddress]]
  def getAllUserAddress(person: String):Future[Seq[UserAddress]]
}

object UserAddressService{
  def apply: UserAddressService = new UserAddressServiceImpl()
}