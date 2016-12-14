package repositories.job

import conf.connection.DataConnection
import domain.job.Job
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by SONY on 2016-12-14.
  */
class JobRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    JobRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val job = Job(
      "ORG100",
      "100",
      "200",
      "2",
      "12345",
      "jobG",
      new DateTime(2016, 11, 9, 12, 0, 0, 0),
      "details")

    val result = Await.result(JobRepository.save(job), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetJob") {
    val result = Await.result(JobRepository.getCompanyJobs("ORG100"), 2.minutes)
    assert(result.head.jobId === "FS100")
  }

  override protected def afterEach(): Unit = {
    JobRepository.truncate().future()
  }
}
