package services.users

import com.websudos.phantom.dsl._
import domain.users.{User, UserRole}
import services.users.impl.UserServiceImpl

import scala.concurrent.Future

/**
  * Created by SONY on 2016-10-24.
  */
trait UserService
{
  def createOrUpdate(user: User): Future[ResultSet]

  def getUser(organisationId: String, userId: String): Future[Option[User]]

  def getUserByEmail(email: String): Future[Seq[User]]

  def getOrganisationUsers(organisationId: String): Future[Seq[User]]

  def getAllUser(): Future[Seq[User]]

  def getRole(user: User):Future[UserRole]

  def getRoles(user: User):Future[Seq[UserRole]]

}

object UserService{
  def apply(): UserService = new UserServiceImpl()
}
