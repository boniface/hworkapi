package services.storage.impl
import com.websudos.phantom.dsl.ResultSet
import domain.storage.FileResults
import services.Service
import services.storage.FileResultsService
import repositories.storage.FileResultsRepository
import scala.concurrent.Future
/**
  * Created by Lonwabo on 11/1/2016.
  */
class FileResultsServiceImpl extends FileResultsService with Service{
  override def createOrUpdate(fileResults: FileResults): Future[ResultSet] = {
    FileResultsRepository.save(fileResults)
  }

  override def getFileResultsById(organisationId: String, fileResultsId: String): Future[Option[FileResults]] = {
    FileResultsRepository.getFileResultById(organisationId,fileResultsId)
  }

  override def getAllFileResults(): Future[Seq[FileResults]] = {
    FileResultsRepository.findAll
  }
}
