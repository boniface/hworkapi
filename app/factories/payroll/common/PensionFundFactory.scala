package factories.payroll.common

import domain.payroll.common.PensionFund

/**
  * Created by SONY on 2016-10-18.
  */
class PensionFundFactory
{
  def createPensionFund(values: Map[String, String]): PensionFund=
    PensionFund(organisationId = values("organisationId"), pensionFundId = values("pensionFundId"), name = values("name"))

}
