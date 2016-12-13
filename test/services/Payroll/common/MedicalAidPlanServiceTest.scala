package services.common.util.Payroll.common

import domain.payroll.common.MedicalAidPlan
import org.scalatest.FunSuite
import services.payroll.common.MedicalAidPlanService

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class MedicalAidPlanServiceTest extends FunSuite {

  val details: Map[String, String] = Map()
  test("testSaveOrUpdate") {
    val medicalAidPlan = MedicalAidPlan("MA100", "Medical Aid Plan Name", details)
    val result = Await.result(MedicalAidPlanService.apply.createOrUpdate(medicalAidPlan), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetMedicalAidPlan") {
    val result = Await.result(MedicalAidPlanService.apply.getMedicalAidPlans("MA100"), 2.minutes)
    assert(result.head.name === "Medical Aid Plan Name")
  }
}
