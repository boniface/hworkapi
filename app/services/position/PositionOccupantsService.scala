package services.position

import com.websudos.phantom.dsl._
import domain.position.PositionOccupants
import services.position.Impl.PositionOccupantsServiceImpl

import scala.concurrent.Future

/**
  * Created by Theo on 23-Nov-16.
  */
trait PositionOccupantsService {
  def createOrUpdate(positionId: PositionOccupants): Future[ResultSet]

  def getPositionOccupantById(positionId: String, positionOccupantId: String): Future[Option[PositionOccupants]]

  def getPositionOccupants(positionId: String): Future[Seq[PositionOccupants]]
}

object PositionOccupantsService{
  def apply: PositionOccupantsService = new PositionOccupantsServiceImpl()
}