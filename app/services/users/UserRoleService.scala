package services.users

import com.websudos.phantom.dsl._
import domain.users.UserRole
import services.users.impl.UserRoleServiceImpl

import scala.concurrent.Future

/**
  * Created by SONY on 2016-10-23.
  */
trait UserRoleService {
  def createOrUpdate(personRole: UserRole): Future[ResultSet]

  def getUserRoles(organisationId: String, userId: String): Future[Seq[UserRole]]

  def getSystemRoles: Future[Seq[UserRole]]

  def getOrganisationRoles(organisationId: String): Future[Seq[UserRole]]
}

object UserRoleService {
  def apply: UserRoleService = new UserRoleServiceImpl()
}
