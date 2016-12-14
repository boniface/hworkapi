package repositories.job

import conf.connection.DataConnection
import domain.job.JobEvent
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by SONY on 2016-12-14.
  */
class JobEventRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    JobRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val jobEvent = JobEvent(
      "JOB100",
      "100",
      new DateTime(2016, 11, 9, 12, 0, 0, 0),
      "details")

    val result = Await.result(JobEventRepository.save(jobEvent), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetJobEvent") {
    val result = Await.result(JobEventRepository.getJobEvents("JOB100"), 2.minutes)
    assert(result.head.jobId === "100")
  }

  override protected def afterEach(): Unit = {
    JobEventRepository.truncate().future()
  }
}
