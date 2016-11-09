package services.common.demographics

import com.websudos.phantom.dsl._
import domain.common.demographics.MaritalStatus
import services.common.demographics.Impl.MaritalStatusServiceImpl

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/10/27.
  */
trait MaritalStatusService {

  def createOrUpdate(maritalStatus:MaritalStatus):Future[ResultSet]

  def getMaritalStatusById(maritalStatus:String):Future[Option[MaritalStatus]]

  def getMaritalStatuses(maritalStatus:String):Future[Seq[MaritalStatus]]

}

object MaritalStatusService{
  def apply: MaritalStatusService = new MaritalStatusServiceImpl()
}