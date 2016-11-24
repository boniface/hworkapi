package services.storage
import com.websudos.phantom.dsl.ResultSet
import domain.storage.StorageUrl
import services.storage.impl.StorageUrlServiceImpl

import scala.concurrent.Future
/**
  * Created by Lonwabo on 11/1/2016.
  */
trait StorageUrlService {
  def createOrUpdate(link: StorageUrl): Future[ResultSet]
  def getStorageUrlById(organisationId : String,storageUrlId: String):Future[Option[StorageUrl]]
  def getAllStorageUrl():Future[Seq[StorageUrl]]
}

object StorageUrlService{
  def apply: StorageUrlService = new StorageUrlServiceImpl()
}
