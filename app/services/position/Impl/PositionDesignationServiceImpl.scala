package services.position.Impl

import com.websudos.phantom.dsl._
import domain.position.PositionDesignation
import repositories.position.PositionDesignationRepository
import services.Service
import services.position.PositionDesignationService

import scala.concurrent.Future

/**
  * Created by Theo on 22-Nov-16.
  */
class PositionDesignationServiceImpl extends PositionDesignationService with Service {
  override def createOrUpdate(positionDesignation: PositionDesignation): Future[ResultSet] = {
    PositionDesignationRepository.save(positionDesignation)
  }

  override def getDesignationById(positionId: String, positionDesignationId: String): Future[Option[PositionDesignation]] = {
    PositionDesignationRepository.getDesignationById(positionId, positionDesignationId)
  }

  override def getPositionDesignations(positionId: String): Future[Seq[PositionDesignation]] = {
    PositionDesignationRepository.getPositionDesignations(positionId)
  }

}
