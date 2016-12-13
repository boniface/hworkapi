package services.position

import com.websudos.phantom.dsl.ResultSet
import domain.position.PositionPackage
import play.api.cache.redis.Builders.Future
import services.position.Impl.PositionPackageServiceImpl

/**
  * Created by Yusiry on 11/21/2016.
  */
trait PositionPackageService {

    def createOrUpdate(positionPackage: PositionPackage): Future[ResultSet]

    def getPositionPackageById(positionId: String, id: String): Future[Option[PositionPackage]]

    def getPositionPackages(positionId: String): Future[Seq[PositionPackage]]
}


object PositionPackageService{
  def apply: PositionPackageService = new PositionPackageServiceImpl()
}
