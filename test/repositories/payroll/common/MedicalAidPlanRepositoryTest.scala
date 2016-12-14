package repositories.common.util.payroll.common

import conf.connection.DataConnection
import domain.payroll.common.MedicalAidPlan
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.payroll.common.MedicalAidPlanRepository
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class MedicalAidPlanRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    MedicalAidPlanRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val details: Map[String, String] = Map()
    val medicalAidPlan = MedicalAidPlan(
      "MA100",
      "Medical Aid Plan Name",
      details)

    val result = Await.result(MedicalAidPlanRepository.save(medicalAidPlan), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetMedicalAidPlan") {
    val result = Await.result(MedicalAidPlanRepository.getMedicalAidPlanById("MA100"), 2.minutes)
    assert(result.head.name === "Medical Aid Plan Name")
  }

  test("testFindAllMedicalAidPlan") {
    val result = Await.result(MedicalAidPlanRepository.findAll, 2.minutes)
    assert( result.size > 0)
  }
  override protected def afterEach(): Unit = {
    MedicalAidPlanRepository.truncate().future()
  }
}



