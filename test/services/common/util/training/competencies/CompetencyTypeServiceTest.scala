package services.common.util.training.competencies

import domain.common.util.Mail
import domain.training.competencies.CompetencyType
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.Training.CompetencyTypeService
import services.common.util.MailService
import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by gavin.ackerman on 2016-12-15.
 */
class CompetencyTypeServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val competencyType = CompetencyType(
      "comp1",
      "good"
    )

    val result = Await.result(CompetencyTypeService.apply.createOrUpdate(competencyType), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCompetencyType") {
    val result = Await.result(CompetencyTypeService.apply.getCompetencyTypeById("comp1"), 2.minutes)
    assert( result.head.name === "good")
  }
}
