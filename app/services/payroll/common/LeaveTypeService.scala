package app.services.payroll.common

import app.services.payroll.common.Impl.LeaveTypeServiceImpl
import domain.payroll.common.LeaveType
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
  * Created by Malu.Mukendi on 2016-11-11.
  */
trait LeaveTypeService {

  def createOrUpdate(leaveType: LeaveType):Future[ResultSet]

  def getFileResultById(organisationId: String, leaveTypeId: String): Future[Option[LeaveType]]

  def getLeaveType(organisationId: String): Future[Seq[LeaveType]]

}
object LeaveTypeService{
  def apply: LeaveTypeService = new LeaveTypeServiceImpl()

}