package services.common.demographics.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.common.demographics.MaritalStatus
import repositories.common.demographics.MaritalStatusRepository
import services.Service
import services.common.demographics.MaritalStatusService

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/10/27.
  */
class MaritalStatusServiceImpl extends MaritalStatusService with Service {
  override def createOrUpdate(maritalStatus: MaritalStatus): Future[ResultSet] = {
    MaritalStatusRepository.save(maritalStatus)
  }

  override def getMaritalStatusById(id: String): Future[Option[MaritalStatus]] = {
    MaritalStatusRepository.getMaritalStatusById(id)
  }

  override def getMaritalStatuses(id: String): Future[Seq[MaritalStatus]] = {
    MaritalStatusRepository.getMaritalStatuses(id)
  }
}
