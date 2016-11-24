package services.payroll.common.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.payroll.common.MedicalAidPlan
import repositories.payroll.common.MedicalAidPlanRepository
import services.Service
import services.payroll.common.MedicalAidPlanService
import scala.concurrent.Future

/**
  * Created by Malu.Mukendi on 2016-11-21.
  */
class MedicalAidPlanServiceImpl extends MedicalAidPlanService with Service{
  override def createOrUpdate(medicalAidPlan: MedicalAidPlan): Future[ResultSet] = {
    MedicalAidPlanRepository.save(medicalAidPlan)
  }
  override def getMedicalAidPlanById(medicalAidId: String): Future[Option[MedicalAidPlan]] = {
    MedicalAidPlanRepository.getMedicalAidPlanById(medicalAidId)
  }
  override def getMedicalAidPlans(medicalAidId: String): Future[Seq[MedicalAidPlan]] = {
    MedicalAidPlanRepository.getMedicalAidPlans(medicalAidId)
  }
}