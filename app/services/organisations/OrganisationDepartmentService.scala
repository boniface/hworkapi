package services.organisations

import com.websudos.phantom.dsl._
import domain.organisations.OrganisationDepartment
import services.organisations.Impl.OrganisationDepartmentServiceImpl

import scala.concurrent.Future

/**
 * Created by Isiphile on 2016/10/21.
 */
trait OrganisationDepartmentService {
  def createOrUpdate(organisationDepartment: OrganisationDepartment): Future[ResultSet]

  def getFileResultById(organisationId: String, organisationDepartmentId: String): Future[Option[OrganisationDepartment]]

  def getOrganisationDepartment(organisationId: String): Future[Seq[OrganisationDepartment]]

  def deleteById(organisationId: String, organisationDepartmentId: String): Future[ResultSet]
}
object OrganisationDepartmentService{
  def apply: OrganisationDepartmentService = new OrganisationDepartmentServiceImpl()
}
