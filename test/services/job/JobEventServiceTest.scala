package services.job

import domain.job.JobEvent
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by SONY on 2016-12-14.
  */
class JobEventServiceTest extends FunSuite {

  val details: Map[String, String] = Map()
  test("testSaveOrUpdate") {
    val jobEvent = JobEvent("ORG100", "FS100",  new DateTime, "details")
    val result = Await.result(JobEventService.apply.createOrUpdate(jobEvent), 2.minutes)
    assert(result.isExhausted)
  }

  test("testJobEvent") {
    val result = Await.result(JobEventService.apply.getJobEvents("ORG100"), 2.minutes)
    assert(result.head.jobEventId === "FS100")
  }
}