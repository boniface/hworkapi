package services.position.Impl

import com.websudos.phantom.dsl._
import domain.position.PositionEvent
import repositories.position.PositionEventRepository
import services.Service
import services.position.PositionEventService

import scala.concurrent.Future

/**
  * Created by Theo on 23-Nov-16.
  */
class PositionEventServiceImpl extends PositionEventService with Service {
  override def createOrUpdate(positionEvent: PositionEvent): Future[ResultSet] = {
    PositionEventRepository.save(positionEvent)
  }

  override def getEventById(positionId: String, positionEventId: String): Future[Option[PositionEvent]] = {
    PositionEventRepository.getPositionEvent(positionId, positionEventId)
  }

  override def getPositionEvents(positionId: String): Future[Seq[PositionEvent]] = {
    PositionEventRepository.getPositionEvents(positionId)
  }

}
