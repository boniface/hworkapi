package services.common.util.training.courses

import domain.common.util.Mail
import domain.training.courses.Criteria
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.Training.CriteriaService
import services.common.util.MailService
import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by gavin.ackerman on 2016-12-15.
 */
class CriteriaServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val criteria = Criteria(
      "criteria54",
      "course324"
    )


    val result = Await.result(CriteriaService.apply.createOrUpdate(criteria), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCriteria") {
    val result = Await.result(CriteriaService.apply.getCriteriaById("criteria54"), 2.minutes)
    assert( result.head.name === "course324")
  }

}
