package services.payroll.common

import com.websudos.phantom.dsl.ResultSet
//import com.websudos.phantom.reactivestreams._
import domain.payroll.common.MedicalAidPlan
//import repositories.payroll.common.MedicalAidPlanRepository._
import services.payroll.common.Impl.MedicalAidPlanServiceImpl
import scala.concurrent.Future

/**
  * Created by Malu.Mukendi on 2016-11-19.
  */
trait MedicalAidPlanService {

  def createOrUpdate(medicalAidPlan:MedicalAidPlan):Future[ResultSet]

  def getMedicalAidPlanById(medicalAidId: String): Future[Option[MedicalAidPlan]]

  def getMedicalAidPlans(medicalAidId: String): Future[Seq[MedicalAidPlan]]
}
object MedicalAidPlanService{
  def apply: MedicalAidPlanService = new MedicalAidPlanServiceImpl()
}