package services.common.util

import com.websudos.phantom.dsl._
import domain.common.util.Status
import services.common.util.Impl.StatusServiceImpl

import scala.concurrent.Future


trait StatusService {

  def createOrUpdate(status: Status): Future[ResultSet]
  def getStatus(statusId: String): Future[Seq[Status]]

}

object StatusService{
  def apply: StatusService = new StatusServiceImpl()
}