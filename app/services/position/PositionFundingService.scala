package services.position

import com.websudos.phantom.dsl.ResultSet
import domain.position.PositionFunding
import play.api.cache.redis.Builders.Future
import services.position.Impl.PositionFundingServiceImpl

/**
  * Created by Yusiry on 11/21/2016.
  */
trait PositionFundingService {
  def createOrUpdate(positionFunding: PositionFunding): Future[ResultSet]

  def getPositionFundingById(positionId: String, id: String): Future[Option[PositionFunding]]
}

object PositionFundingService{
  def apply: PositionFundingService = new PositionFundingServiceImpl()
}
