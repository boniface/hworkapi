package factories.payroll.common

import domain.payroll.common.PensionFundPlan
import org.joda.time.DateTime

/**
  * Created by SONY on 2016-10-18.
  */
class PensionFundPlanFactory
{
  def createPensionFundPlan(values: Map[String, String], date: DateTime, details: Map[String, String]): PensionFundPlan=
    PensionFundPlan(pensionFundId = values("pensionFundId"), date = date, details = details)

}
