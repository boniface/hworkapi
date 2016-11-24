package services.position.Impl

import com.websudos.phantom.dsl._
import domain.position.PositionOccupants
import repositories.position.PositionOccupantsRepository
import services.Service
import services.position.PositionOccupantsService

import scala.concurrent.Future

/**
  * Created by Theo on 23-Nov-16.
  */
class PositionOccupantsServiceImpl extends PositionOccupantsService with Service {
  override def createOrUpdate(positionOccupants: PositionOccupants): Future[ResultSet] = {
    PositionOccupantsRepository.save(positionOccupants)
  }

  override def getPositionOccupantById(positionId: String, positionOccupantId: String): Future[Option[PositionOccupants]] = {
    PositionOccupantsRepository.getPositionOccupant(positionId, positionOccupantId)
  }

  override def getPositionOccupants(positionId: String): Future[Seq[PositionOccupants]] = {
    PositionOccupantsRepository.getPositionOccupants(positionId)
  }
}