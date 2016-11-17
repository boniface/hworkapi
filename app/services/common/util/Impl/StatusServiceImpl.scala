package services.common.util.Impl

import repositories.common.util.StatusRepository
import com.websudos.phantom.dsl.ResultSet
import domain.common.util.Status
import services.Service
import services.common.util.StatusService

import scala.concurrent.Future

class StatusServiceImpl extends StatusService with Service{
  override def createOrUpdate(status: Status): Future[ResultSet] = {
    StatusRepository.save(status)
  }

  override def getStatus(statusId: String): Future[Seq[Status]] = {
    StatusRepository.getStatus(statusId)
  }
}
