package services.storage

import domain.storage.FileMeta
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by LuxoloM on 2016/12/14.
  */
class FileMetaServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val fileMeta = FileMeta("ORG100", "FLName","LT100")
    val result = Await.result(FileMetaService.apply.createOrUpdate(fileMeta), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetFileMeta") {
    val result = Await.result(FileMetaService.apply.getFileMetaById("ORG100"), 2.minutes)
    assert(result.head.organisationId === "ORG100")
  }
}