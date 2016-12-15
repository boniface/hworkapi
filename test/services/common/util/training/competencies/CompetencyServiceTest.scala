package services.common.util.training.competencies

import domain.common.util.Mail
import domain.training.competencies.Competency
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.Training.CompetencyService
import services.common.util.MailService
import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by gavin.ackerman on 2016-12-15.
 */
class CompetencyServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val competency = Competency(
      "comp2",
      "goodComp",
      "comp1"
    )

    val result = Await.result(CompetencyService.apply.createOrUpdate(competency), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCompetency") {
    val result = Await.result(CompetencyService.apply.getCompetencyById("comp2"), 2.minutes)
    assert( result.head.name === "goodComp")
  }
}
