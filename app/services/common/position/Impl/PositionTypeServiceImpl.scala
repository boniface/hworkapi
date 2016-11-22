package services.common.position.Impl

import repositories.common.position.PositionTypeRepository
import com.websudos.phantom.dsl.ResultSet
import domain.common.position.PositionType
import services.Service
import services.common.position.PositionTypeService

import scala.concurrent.Future

class PositionTypeServiceImpl extends PositionTypeService with Service {
  override def createOrUpdate(positionType: PositionType): Future[ResultSet] = {
    PositionTypeRepository.save(positionType)
  }

  override def getPositionType(locationId: String): Future[Seq[PositionType]] = {
    PositionTypeRepository.getPositionType(locationId)
  }
}
