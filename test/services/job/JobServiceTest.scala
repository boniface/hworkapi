package services.job

import domain.job.Job
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by SONY on 2016-12-14.
  */
class JobServiceTest extends FunSuite {

  val details: Map[String, String] = Map()
  test("testSaveOrUpdate") {
    val job = Job("ORG100", "FS100", "1", "Til", "468", "jobDescri",  new DateTime, "RSA")
    val result = Await.result(JobService.apply.createOrUpdate(job), 2.minutes)
    assert(result.isExhausted)
  }

  test("testJob") {
    val result = Await.result(JobService.apply.getCompanyJobs("ORG100"), 2.minutes)
    assert(result.head.jobClassificationId === "FS100")
  }
}