package factories.payroll.common
import domain.payroll.common.PensionFundPlan
import org.joda.time.DateTime
/**
  * Created by Malu.Mukendi on 2016-09-30.
  */
object PensionFundPlanFactory {
  def createPensionFundPlan(values:Map[String, String], date:DateTime):PensionFundPlan={
    PensionFundPlan(pensionFundId = values("pensionFundId"),date = date ,details = values)
  }
}
