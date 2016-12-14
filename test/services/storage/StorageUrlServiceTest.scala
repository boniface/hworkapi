package services.storage

import domain.storage.StorageUrl
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by LuxoloM on 2016/12/14.
  */
class StorageUrlServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val storageUrl = StorageUrl("ORG100", "LT100", "sto", "https://fr.com")
    val result = Await.result(StorageUrlService.apply.createOrUpdate(storageUrl), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetStorageUrl") {
    val result = Await.result(StorageUrlService.apply.getStorageUrlById("ORG100", "LT100"), 2.minutes)
    assert(result.head.organisationId === "ORG100")
  }

}
