package services.common.util.job

import domain.common.job.JobClassification
import org.scalatest.FunSuite
import services.common.job.JobClassificationService

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Aphiwe on 2016/12/13.
  */
class JobClassificationServiceTest extends FunSuite{

  test("testSaveOrUpdate") {
    val jobClassification = JobClassification(
      "JobClassificationID",
      "Developer",
      "Programmer",
      "123",
      "321",
      "897","Available")

    val result = Await.result(JobClassificationService.apply.createOrUpdate(jobClassification), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetMail") {
    val result = Await.result(JobClassificationService.apply.getJobClassificationById("SITEID"), 2.minutes)
    assert( result.head.currentTitle === "Developer")
  }
}
