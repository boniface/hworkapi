package services.position.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.position.Position
import play.api.cache.redis.Builders.Future
import repositories.position.PositionRepository
import services.Service
import services.position.PositionService

/**
  * Created by Yusiry on 11/21/2016.
  */
class PositionServiceImpl extends PositionService with Service{
  override def createOrUpdate(position: Position): Future[ResultSet] = {
    PositionRepository.save(position);
  }

  override def getPositionById(companyId: String, id: String): Future[Option[Position]] = {
    PositionRepository.getPositionById(companyId, id);
  }

  override def getCompanyPositions(companyId: String): Future[Seq[Position]] = {
    PositionRepository.getCompanyPositions(companyId);
  }
}
