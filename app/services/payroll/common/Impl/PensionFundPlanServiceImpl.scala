package services.payroll.common.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.payroll.common.PensionFundPlan
import repositories.payroll.common.PensionFundPlanRepository
import services.Service
import services.payroll.common.PensionFundPlanService
import scala.concurrent.Future

/**
  * Created by Malu.Mukendi on 2016-11-19.
  */
class PensionFundPlanServiceImpl extends PensionFundPlanService with Service {
  override def createOrUpdate(pensionFundPlan: PensionFundPlan): Future[ResultSet] = {
    PensionFundPlanRepository.save(pensionFundPlan)
  }

  override def getPensionFundPlanById(pensionFundId: String): Future[Option[PensionFundPlan]] = {
    PensionFundPlanRepository.getPensionFundPlanById(pensionFundId)
  }

  override def getPensionFundPlans(pensionFundId: String): Future[Seq[PensionFundPlan]] = {
    PensionFundPlanRepository.getPensionFundPlans(pensionFundId)
  }
}

