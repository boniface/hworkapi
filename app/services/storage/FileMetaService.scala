package services.storage

import com.websudos.phantom.dsl.ResultSet
import domain.organisations.Organisation
import domain.storage.FileMeta
import services.storage.impl.FileMetaServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 11/1/2016.
  */
trait FileMetaService {
  def createOrUpdate(fileMeta: FileMeta):Future[ResultSet]
  def getFileMetaById(organisationId: String):Future[Option[FileMeta]]
  def getAllFileMeta():Future[Seq[FileMeta]]
}

object FileMetaService{
  def apply: FileMetaService = new FileMetaServiceImpl()
}
