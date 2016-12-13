package repositories.storage

import conf.connection.DataConnection
import domain.storage.FileResults
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by LuxoloM on 2016/12/13.
  */
class FileResultsRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    FileMetaRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val fileResults = FileResults(
      "ORG100",
      "IMF100",
      "https://do.com",
      Option("IMF")
      )

    val result = Await.result(FileResultsRepository.save(fileResults), 2.minutes)
    assert(result.isExhausted)
  }

  test("testFileResults") {
    val result = Await.result(FileResultsRepository.getFileResults("ORG100"), 2.minutes)
    assert(result.head.size === "IMF")
  }

  test("testFindAllFileResults") {
    val result = Await.result(FileResultsRepository.findAll, 2.minutes)
    assert( result.size > 0)
  }
  override protected def afterEach(): Unit = {
    FileResultsRepository.truncate().future()
  }
}
