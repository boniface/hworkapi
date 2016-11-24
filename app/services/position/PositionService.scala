package services.position

import com.websudos.phantom.dsl.ResultSet
import domain.position.Position
import play.api.cache.redis.Builders.Future
import services.position.Impl.PositionServiceImpl

/**
  * Created by Yusiry on 11/21/2016.
  */
trait PositionService {
  def createOrUpdate(position: Position): Future[ResultSet]

  def getPositionById(companyId: String, id: String): Future[Option[Position]]

  def getCompanyPositions(companyId: String): Future[Seq[Position]]
}


object PositionService{
  def apply: PositionService = new PositionServiceImpl()
}