package services.position.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.position.PositionFunding
import play.api.cache.redis.Builders.Future
import repositories.position.PositionFundingRepository
import services.position.PositionFundingService
import services.Service

/**
  * Created by Yusiry on 11/21/2016.
  */
class PositionFundingServiceImpl extends PositionFundingService with Service{
  override def createOrUpdate(positionFunding: PositionFunding): Future[ResultSet] = {
    PositionFundingRepository.save(positionFunding);
  }

  override def getPositionFundingById(positionFundingId: String, id: String): Future[Option[PositionFunding]] = {
    PositionFundingRepository.getPositionFunding(positionFundingId, id);
  }

}
