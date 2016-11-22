package factories.payroll.funding

import domain.payroll.funding.FundingSources
import org.joda.time.DateTime

/**
  * Created by SONY on 2016-10-18.
  */
class FundingSourcesFactory
{
  def createFundingSources(values: Map[String, String], date: DateTime, details: Map[String, String]): FundingSources=
    FundingSources(organisationId = values("organisationId"), fundingSourcesId = values("fundingSourcesId"),  name = values("name"),
      costCenterNumber = values("costCenterNumber"), date = date, details = details)

}
