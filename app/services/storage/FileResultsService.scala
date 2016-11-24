package services.storage

import com.websudos.phantom.dsl._
import domain.storage.FileResults
import services.storage.impl.FileResultsServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 11/1/2016.
  */
trait FileResultsService {
  def createOrUpdate(fileResults: FileResults):Future[ResultSet]
  def getFileResultsById(organisationId: String,fileResultsId: String):Future[Option[FileResults]]
  def getAllFileResults():Future[Seq[FileResults]]
}

object FileResultsService{
  def apply: FileResultsService = new FileResultsServiceImpl()
}