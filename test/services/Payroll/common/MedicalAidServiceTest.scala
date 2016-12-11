package services.common.util.Payroll.common

import app.services.payroll.common.MedicalAidService
import domain.payroll.common.MedicalAid
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class MedicalAidServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val medicalAid = MedicalAid("ORG100", "MA100", "Medical Aid Name", "12345")
    val result = Await.result(MedicalAidService.apply.createOrUpdate(medicalAid), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetMedicalAid") {
    val result = Await.result(MedicalAidService.apply.getMedicalAid("ORG100"), 2.minutes)
    assert(result.head.name === "Medical Aid Name")
  }
}
