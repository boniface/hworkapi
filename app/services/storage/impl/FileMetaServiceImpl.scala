package services.storage.impl
import com.websudos.phantom.dsl.ResultSet
import domain.organisations.Organisation
import domain.storage.FileMeta
import services.Service
import services.storage.FileMetaService
import repositories.storage.FileMetaRepository
import scala.concurrent.Future
/**
  * Created by Lonwabo on 11/1/2016.
  */
class FileMetaServiceImpl extends FileMetaService with Service{
  override def createOrUpdate(fileMeta: FileMeta): Future[ResultSet] = {
    FileMetaRepository.save(fileMeta)
  }

  override def getFileMetaById(organisationId: String): Future[Option[FileMeta]] = {
    FileMetaRepository.getFileResultById(organisationId)
  }

  override def getAllFileMeta(): Future[Seq[FileMeta]] = {
    FileMetaRepository.findAll
  }
}
