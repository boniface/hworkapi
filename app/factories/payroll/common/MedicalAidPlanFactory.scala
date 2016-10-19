package factories.payroll.common

import domain.payroll.common.MedicalAidPlan

/**
  * Created by SONY on 2016-10-18.
  */
class MedicalAidPlanFactory
{
  def createMedicalAidPlan(values: Map[String, String], details: Map[String, String]): MedicalAidPlan=
    MedicalAidPlan(medicalAidId = values("medicalAidId"), name = values("name"),details = details)

}
