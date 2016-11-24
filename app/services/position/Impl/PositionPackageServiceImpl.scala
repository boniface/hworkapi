package services.position.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.position.PositionPackage
import play.api.cache.redis.Builders.Future
import repositories.position.PositionPackageRepository
import services.Service
import services.position.PositionPackageService

/**
  * Created by Yusiry on 11/21/2016.
  */
class PositionPackageServiceImpl extends PositionPackageService with Service {
  override def createOrUpdate(positionPackage: PositionPackage): Future[ResultSet] = {
    PositionPackageRepository.save(positionPackage);
  }

  override def getPositionPackageById(positionPackageId: String, id: String): Future[Option[PositionPackage]] = {
    PositionPackageRepository.getPositionPackage(id, positionPackageId);
  }

  override def getPositionPackages(positionPackageId: String): Future[Seq[PositionPackage]] = {
    PositionPackageRepository.getPositionPackages(positionPackageId);
  }
}
