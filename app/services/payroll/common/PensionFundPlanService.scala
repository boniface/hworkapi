package services.payroll.common

import com.websudos.phantom.dsl.ResultSet
import domain.payroll.common.PensionFundPlan
import services.payroll.common.Impl.PensionFundPlanServiceImpl
import scala.concurrent.Future

/**
  * Created by Malu.Mukendi on 2016-11-19.
  */
trait PensionFundPlanService {

  def createOrUpdate(pensionFundPlan:PensionFundPlan):Future[ResultSet]

  def getPensionFundPlanById(pensionFundId: String): Future[Option[PensionFundPlan]]

  def getPensionFundPlans(pensionFundId: String): Future[Seq[PensionFundPlan]]
}
object PensionFundPlanService{
  def apply: PensionFundPlanService = new PensionFundPlanServiceImpl()
}
