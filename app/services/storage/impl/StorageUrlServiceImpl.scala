package services.storage.impl
import com.websudos.phantom.dsl.ResultSet
import domain.storage.StorageUrl
import services.Service
import services.storage.StorageUrlService
import repositories.storage.StorageUrlRepository

import scala.concurrent.Future
/**
  * Created by Lonwabo on 11/1/2016.
  */
class StorageUrlServiceImpl extends StorageUrlService with Service{
  override def createOrUpdate(link: StorageUrl): Future[ResultSet] = {
    StorageUrlRepository.save(link)
  }

  override def getStorageUrlById(organisationId: String, storageUrlId: String): Future[Option[StorageUrl]] = {
    StorageUrlRepository.getFileResultById(organisationId,storageUrlId)
  }

  override def getAllStorageUrl(): Future[Seq[StorageUrl]] = {
    StorageUrlRepository.findAll
  }
}
