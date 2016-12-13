package repositories.common.util.job

import conf.connection.DataConnection
import domain.common.education.EducationType
import domain.common.job.JobClassification
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.education.EducationTypeRepository
import repositories.common.job.JobClassificationRepository

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Aphiwe on 2016/12/13.
  */
class JobClassificationRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    JobClassificationRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val jobClassification = JobClassification(
      "JobClassificationID",
      "Developer","Programmer","123","321","098","Available")

    val result = Await.result(JobClassificationRepository.save(jobClassification), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetJobClassification") {
    val result = Await.result(JobClassificationRepository.getJobClassificationById("EducationTypeID"), 2.minutes)
    assert( result.head.currentTitle === "Developer")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    EducationTypeRepository.truncate().future()
  }
}
