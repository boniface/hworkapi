package app.services.payroll.common.Impl

import app.services.payroll.common.LeaveTypeService
import domain.payroll.common.LeaveType
import repositories.payroll.common.LeaveTypeRepository
import services.Service
import com.websudos.phantom.dsl._
import scala.concurrent.Future

/**
  * Created by Malu.Mukendi on 2016-11-11.
  */
class LeaveTypeServiceImpl extends LeaveTypeService with Service{

  override def createOrUpdate(leaveType: LeaveType): Future[ResultSet] ={
    LeaveTypeRepository.save(leaveType)
  }

  override def getFileResultById(organisationId: String, leaveTypeId: String): Future[Option[LeaveType]] = {
    LeaveTypeRepository.getFileResultById(organisationId, leaveTypeId)
  }

  override def getLeaveType(organisationId: String): Future[Seq[LeaveType]] = {
    LeaveTypeRepository.getLeaveType(organisationId)
  }
}
