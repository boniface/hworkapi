package repositories.common.util.payroll.common

import conf.connection.DataConnection
import domain.payroll.common.MedicalAid
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.payroll.common.MedicalAidRepository
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class MedicalAidRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    MedicalAidRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val medicalAid = MedicalAid(
      "ORG100",
      "MA100",
      "Medical Aid Name",
      "12345")

    val result = Await.result(MedicalAidRepository.save(medicalAid), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetMedicalAid") {
    val result = Await.result(MedicalAidRepository.getMedicalAid("ORG100"), 2.minutes)
    assert(result.head.name === "Medical Aid Name")
  }

  test("testFindAllMedicalAid") {
    val result = Await.result(MedicalAidRepository.findAll, 2.minutes)
    assert( result.size > 0)
  }
  override protected def afterEach(): Unit = {
    MedicalAidRepository.truncate().future()
  }
}
