package services.common.demographics.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.common.demographics.Role
import repositories.common.demographics.RoleRepository
import services.Service
import services.common.demographics.RoleService

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/10/27.
  */
class RoleServiceImpl extends RoleService with Service{
  override def createOrUpdate(role: Role): Future[ResultSet] = {
    RoleRepository.save(role)
  }

  override def getRoleById(id: String): Future[Option[Role]] = {
    RoleRepository.getRoleById(id)
  }

  override def getRoles(id: String): Future[Seq[Role]] = {
    RoleRepository.getRoles(id)
  }
}
