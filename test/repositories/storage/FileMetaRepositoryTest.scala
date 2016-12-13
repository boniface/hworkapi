package repositories.storage

import conf.connection.DataConnection
import domain.storage.FileMeta
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await

/**
  * Created by LuxoloM on 2016/12/13.
  */
class FileMetaRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    FileMetaRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val fileMeta = FileMeta(
      "ORG100",
      "IMF",
      "IMF100")

    val result = Await.result(FileMetaRepository.save(fileMeta), 2.minutes)
    assert(result.isExhausted)
  }

  test("testFileMeta") {
    val result = Await.result(FileMetaRepository.getFileMeta("ORG100"), 2.minutes)
    assert(result.head.fileName === "IMF")
  }

  test("testFindAllFileMeta") {
    val result = Await.result(FileMetaRepository.findAll, 2.minutes)
    assert( result.size > 0)
  }
  override protected def afterEach(): Unit = {
    FileMetaRepository.truncate().future()
  }
}