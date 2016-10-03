package factories.payroll.common
import domain.payroll.common.MedicalAid
/**
  * Created by Malu.Mukendi on 2016-09-30.
  */
object MedicalAidFactory {
  def createMedicalAid(values:Map[String, String]):MedicalAid={
    MedicalAid(organisationId = values("organisationId"),medicalAidId = values("medicalAidId"), name = values("name"), code = values("code"))
  }
}
