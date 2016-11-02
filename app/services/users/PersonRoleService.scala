package services.users
import com.websudos.phantom.dsl._
import domain.users.PersonRole
import services.users.impl.PersonRoleServiceImpl

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-23.
  */
trait PersonRoleService {
  def createOrUpdate(personRole: PersonRole): Future[ResultSet]

  def getPersonRoleById(organisationId: String, userId: String, roleId: String): Future[Option[PersonRole]]

  def getPersonRole(organisationId: String): Future[Seq[PersonRole]]
}

object PersonRoleService{
  def apply: PersonRoleService = new PersonRoleServiceImpl()
}
