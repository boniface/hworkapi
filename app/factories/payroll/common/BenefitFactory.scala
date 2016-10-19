package factories.payroll.common

import domain.payroll.common.Benefit
import org.joda.time.DateTime

/**
  * Created by SONY on 2016-10-18.
  */
class BenefitFactory
{
  def createBenefit(values: Map[String, String], date: DateTime): Benefit=
    Benefit(organisationId = values("organisationId"), benefitId = values("benefitId"), name = values("name"), date = date, state = values("state"))

}
