package services.common.util.demographics

import domain.common.demographics.Language
import org.scalatest.FunSuite
import services.common.demographics.LanguageService

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by Aphiwe on 2016/12/13.
  */
class LanguageServiceTest extends FunSuite{

  test("testSaveOrUpdate") {
    val language = Language(
      "LanguageID",
      "English")

    val result = Await.result(LanguageService.apply.createOrUpdate(language), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetLanguage") {
    val result = Await.result(LanguageService.apply.getLanguageById("LanguageProficiencyID"), 2.minutes)
    assert( result.head.name === "English")
  }
}
