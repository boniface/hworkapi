package factories.payroll.common
import domain.payroll.common.MedicalAidPlan

/**
  * Created by Malu.Mukendi on 2016-09-30.
  */
object MedicalAidPlanFactory {
  def createMedicalAidPlan(values:Map[String, String]):MedicalAidPlan={
    MedicalAidPlan(medicalAidId = values("medicalAidId"),name = values("name"), details = values)
  }
}