package services.common.demographics

import com.websudos.phantom.dsl._
import domain.common.demographics.Role
import services.common.demographics.Impl.RoleServiceImpl

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/10/27.
  */
trait RoleService {

  def createOrUpdate(role:Role):Future[ResultSet]

  def getRoleById(role:String):Future[Option[Role]]

  def getRoles(role:String):Future[Seq[Role]]

}

object RoleService{

  def apply: RoleService = new RoleServiceImpl()
}
