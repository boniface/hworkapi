package services.common.position

import com.websudos.phantom.dsl._
import domain.common.position.PositionType
import services.common.position.Impl.PositionTypeServiceImpl

import scala.concurrent.Future


trait PositionTypeService {

  def createOrUpdate(positionType: PositionType): Future[ResultSet]
  def getPositionType(locationId: String): Future[Seq[PositionType]]

}

object PositionTypeService{
  def apply: PositionTypeService = new PositionTypeServiceImpl()
}
