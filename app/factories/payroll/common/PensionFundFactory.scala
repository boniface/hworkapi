package factories.payroll.common
import domain.payroll.common.PensionFund
/**
  * Created by Malu.Mukendi on 2016-09-30.
  */
object PensionFundFactory {
  def createPensionFund(values:Map[String, String]):PensionFund={
    PensionFund(organisationId = values("organisationId"),pensionFundId = values("pensionFundId"), name = values("name"))
  }
}
