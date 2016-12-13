package repositories.common.util.education

/**
  * Created by Aphiwe on 2016/12/13.
  */

import conf.connection.DataConnection
import domain.common.address.OfficeType
import domain.common.education.{DegreeType, EducationType, Evaluation}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.address.OfficeTypeRepository
import repositories.common.education.{DegreeTypeRepository, EducationTypeRepository, EvaluationRepository}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class EvaluationRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    EducationTypeRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val evaluation = Evaluation(
      "EvaluationID",
      "Exam")

    val result = Await.result(EvaluationRepository.save(evaluation), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetEvaluation") {
    val result = Await.result(EvaluationRepository.getEvaluationById("EvaluationID"), 2.minutes)
    assert( result.head.name === "Exam")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    EvaluationRepository.truncate().future()
  }
}
