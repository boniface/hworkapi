package factories.payroll.common

import domain.payroll.common.MedicalAid

/**
  * Created by SONY on 2016-10-18.
  */
class MedicalAidFactory
{
  def createMedicalAid(values: Map[String, String]): MedicalAid=
    MedicalAid(organisationId = values("organisationId"), medicalAidId = values("medicalAidId"), name = values("name"), code = values("code"))


}
