package repositories.storage

import conf.connection.DataConnection
import domain.storage.StorageUrl
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await

/**
  * Created by LuxoloM on 2016/12/13.
  */
class StorageUrlRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    FileMetaRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val storageUrl = StorageUrl(
      "ORG100",
      "IMF100",
      "IMF",
      "https://rew.com")

    val result = Await.result(StorageUrlRepository.save(storageUrl), 2.minutes)
    assert(result.isExhausted)
  }

  test("testStorageUrl") {
    val result = Await.result(StorageUrlRepository.getStorageUrl("ORG100"), 2.minutes)
    assert(result.head.name === "IMF")
  }

  test("testFindAllStorageUrl") {
    val result = Await.result(StorageUrlRepository.findAll, 2.minutes)
    assert( result.size > 0)
  }
  override protected def afterEach(): Unit = {
    StorageUrlRepository.truncate().future()
  }
}