package repositories.common.util.demographics

/**
  * Created by Aphiwe on 2016/12/13.
  */

import conf.connection.DataConnection
import domain.common.demographics.Language
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.demographics.LanguageRepository

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class LanguageRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    LanguageRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val language = Language(
      "languageID",
      "English")

    val result = Await.result(LanguageRepository.save(language), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetLanguage") {
    val result = Await.result(LanguageRepository.getLanguageById("languageID"), 2.minutes)
    assert( result.head.name === "English")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    LanguageRepository.truncate().future()
  }
}
