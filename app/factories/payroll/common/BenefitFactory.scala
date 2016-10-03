package factories.payroll.common
import domain.payroll.common.Benefit

/**
  * Created by Malu.Mukendi on 2016-09-30.
  */
object BenefitFactory {
  def createBenefit(values:Map[String, String]):Benefit={
    Benefit(organisationId = values("organisationId"),benefitId = values("benefitId"), name = values("name"), date = values("date"),state = values("state"))
  }
}
