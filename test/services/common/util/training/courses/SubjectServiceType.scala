package services.common.util.training.courses

import domain.common.util.Mail
import domain.training.courses.Subject
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.Training.SubjectService
import services.common.util.MailService
import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by gavin.ackerman on 2016-12-15.
 */
class SubjectServiceType extends FunSuite {

  test("testSaveOrUpdate") {
    val subject = Subject(
      "sub53",
      "design",
      "subco343",
      "strucutres",
      542
    )


    val result = Await.result(SubjectService.apply.createOrUpdate(subject), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetSubject") {
    val result = Await.result(SubjectService.apply.getSubjectById("sub53"), 2.minutes)
    assert( result.head.credit === 542)
  }

}
