package services.common.util.education

import domain.common.education.DegreeType
import org.scalatest.FunSuite
import services.common.education.DegreeTypeService

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Aphiwe on 2016/12/13.
  */
class DegreeTypeServiceTest extends FunSuite{

  test("testSaveOrUpdate") {
    val degreeType = DegreeType(
      "DegreeTypeID",
      "Diploma")

    val result = Await.result(DegreeTypeService.apply.createOrUpdate(degreeType), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetDegreeType") {
    val result = Await.result(DegreeTypeService.apply.getDegreeTypeById("DegreeTypeID"), 2.minutes)
    assert( result.head.name === "Diploma")
  }
}
