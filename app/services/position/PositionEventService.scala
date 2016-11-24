package services.position

import com.websudos.phantom.dsl._
import domain.position.PositionEvent
import services.position.Impl.PositionEventServiceImpl

import scala.concurrent.Future

/**
  * Created by Theo on 23-Nov-16.
  */
trait PositionEventService {
  def createOrUpdate(positionId: PositionEvent): Future[ResultSet]

  def getEventById(positionId: String, positionEventId: String): Future[Option[PositionEvent]]

  def getPositionEvents(positionId: String): Future[Seq[PositionEvent]]
}

object PositionEventService{
  def apply: PositionEventService = new PositionEventServiceImpl()
}