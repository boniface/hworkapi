package repositories.common.util.demographics

/**
  * Created by Aphiwe on 2016/12/13.
  */

import conf.connection.DataConnection
import domain.common.demographics.{Gender, IdentificationType, LanguageProficiency}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.demographics.{GenderRepository, IdentificationTypeRepository, LanguageProficiencyRepository}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class LanguageProficiencyRepositoryTest extends FunSuite with BeforeAndAfterEach{


  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    LanguageProficiencyRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val languageProficiency = LanguageProficiency(
      "languageProficiencyID",
      "Good")

    val result = Await.result(LanguageProficiencyRepository.save(languageProficiency), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetLanguageProficiency") {
    val result = Await.result(LanguageProficiencyRepository.getLanguageProficiencyById("languageProficiencyID"), 2.minutes)
    assert( result.head.name === "Good")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    LanguageProficiencyRepository.truncate().future()
  }
}
