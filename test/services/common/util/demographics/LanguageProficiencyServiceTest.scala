package services.common.util.demographics

import domain.common.demographics.LanguageProficiency
import org.scalatest.FunSuite
import services.common.demographics.LanguageProficiencyService

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by Aphiwe on 2016/12/13.
  */
class LanguageProficiencyServiceTest extends FunSuite{

  test("testSaveOrUpdate") {
    val languageProficiency = LanguageProficiency(
      "LanguageProficiencyID",
      "Good")

    val result = Await.result(LanguageProficiencyService.apply.createOrUpdate(languageProficiency), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetLanguageProficiency") {
    val result = Await.result(LanguageProficiencyService.apply.getLanguageProficiencyById("LanguageProficiencyID"), 2.minutes)
    assert( result.head.name === "Good")
  }
}
