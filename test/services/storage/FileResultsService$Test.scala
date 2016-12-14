package services.storage

import domain.storage.FileResults
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by LuxoloM on 2016/12/14.
  */
class FileResultsService$Test extends FunSuite {

  test("testSaveOrUpdate") {
    val fileResults = FileResults("ORG100", "LT100","https://fr.com", Option("guu"))
    val result = Await.result(FileResultsService.apply.createOrUpdate(fileResults), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetFileResults") {
    val result = Await.result(FileResultsService.apply.getFileResultsById("ORG100", "LT100"), 2.minutes)
    assert(result.head.organisationId === "ORG100")
  }
}