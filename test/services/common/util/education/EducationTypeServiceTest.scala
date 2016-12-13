package services.common.util.education

import domain.common.education.EducationType
import org.scalatest.FunSuite
import services.common.education.EducationTypeService

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Aphiwe on 2016/12/13.
  */
class EducationTypeServiceTest extends FunSuite{

  test("testSaveOrUpdate") {
    val educationType = EducationType(
      "EducationTypeID",
      "Higher Education")

    val result = Await.result(EducationTypeService.apply.createOrUpdate(degreeType), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetEducationType") {
    val result = Await.result(EducationTypeService.apply.getEducationTypeById("EducationTypeID"), 2.minutes)
    assert( result.head.name === "Diploma")
  }
}
