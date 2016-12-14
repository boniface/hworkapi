package services.organisations.Impl                                                                                                                                                                                                                                                   //Xolela Masebeni(213160447) xmasebeni1@gmail.com                                                                                                                                                                                                                                           //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import com.websudos.phantom.dsl._
import domain.organisations.OrganisationDepartment
import repositories.organisations.OrganisationDepartmentRepository
import services.Service
import services.organisations.OrganisationDepartmentService

import scala.concurrent.Future

/**
 * Created by Isiphile on 2016/10/21.
 */
class OrganisationDepartmentServiceImpl extends  OrganisationDepartmentService with Service{

  override def createOrUpdate(organisationDepartment: OrganisationDepartment): Future[ResultSet] = {
   OrganisationDepartmentRepository.save(organisationDepartment)
 }

 override def getFileResultById(organisationId: String, organisationDepartmentId: String): Future[Option[OrganisationDepartment]] = {
   OrganisationDepartmentRepository.getFileResultById(organisationId,organisationDepartmentId)
 }

 override def getOrganisationDepartment(organisationId: String): Future[Seq[OrganisationDepartment]] = {
   OrganisationDepartmentRepository.getOrganisationDepartment(organisationId)
 }

 override def deleteById(organisationId: String, organisationDepartmentId: String): Future[ResultSet] = {
   OrganisationDepartmentRepository.deleteById(organisationId, organisationDepartmentId)
 }

}
