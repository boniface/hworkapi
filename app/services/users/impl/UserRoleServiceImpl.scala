package services.users.impl

import com.websudos.phantom.dsl._
import domain.users.UserRole
import repositories.users.UserRoleRepository
import services.Service
import services.users.UserRoleService

import scala.concurrent.Future

/**
  * Created by SONY on 2016-10-23.
  */
class UserRoleServiceImpl extends UserRoleService with Service {
  def createOrUpdate(personRole: UserRole): Future[ResultSet] = {
    UserRoleRepository.save(personRole)
  }

  override def getUserRoles(organisationId: String, userId: String): Future[Seq[UserRole]] = {
    UserRoleRepository.getUserRoles(organisationId, userId)
  }

  override def getSystemRoles: Future[Seq[UserRole]] = {
    UserRoleRepository.getSystemRoles
  }

  override def getOrganisationRoles(organisationId: String): Future[Seq[UserRole]] = {
    UserRoleRepository.getOrganisationRoles(organisationId)
  }
}
