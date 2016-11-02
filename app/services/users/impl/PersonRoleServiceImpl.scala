package services.users.impl
import com.websudos.phantom.dsl._
import domain.users.PersonRole
import repositories.users.PersonRoleRepository
import services.Service
import services.users.PersonRoleService

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-23.
  */
class PersonRoleServiceImpl extends PersonRoleService with Service {
  def createOrUpdate(personRole: PersonRole): Future[ResultSet] = {
    PersonRoleRepository.save(personRole)
  }

  def getPersonRoleById(organisationId: String, userId: String, roleId: String): Future[Option[PersonRole]] = {
    PersonRoleRepository.findById(organisationId, userId, roleId)
  }

  def getPersonRole(jobId: String): Future[Seq[PersonRole]] = {
    PersonRoleRepository.getPersonRole(jobId)
  }
}
