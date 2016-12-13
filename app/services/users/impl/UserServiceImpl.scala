package services.users.impl

import com.websudos.phantom.dsl._
import domain.users.{User, UserRole}
import repositories.users.{UserEmailRepository, UserRepository}
import services.Service
import services.users.{UserRoleService, UserService}

import scala.concurrent.Future

/**
  * Created by SONY on 2016-10-24.
  */
class UserServiceImpl extends UserService with Service {
  def createOrUpdate(user: User): Future[ResultSet] = {
    UserRepository.save(user)
  }

  def getUser(organisationId: String, userId: String): Future[Option[User]] = {
    UserRepository.getUser(organisationId, userId)
  }

  def getOrganisationUsers(organisationId: String): Future[Seq[User]] = {
    UserRepository.getOrganisationUsers(organisationId)
  }
  def getAllUser(): Future[Seq[User]] = {
    UserRepository.getAllUser
  }

  override def getRole(user:User): Future[UserRole] = {
    getRoles(user) map(roles => roles.head)
  }

  override def getUserByEmail(email: String): Future[Seq[User]] = {
    UserEmailRepository.getUserByEmail(email)
  }

  override def getRoles(user: User): Future[Seq[UserRole]] = {
    UserRoleService.apply.getUserRoles(user.organisationId,user.userId)
  }
}
