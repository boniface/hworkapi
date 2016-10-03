package factories.payroll.funding
import domain.payroll.funding.FundingSources

/**
  * Created by Malu.Mukendi on 2016-09-30.
  */
object FundingSourcesFactory {
  def createFundingSources(values:Map[String, String]):FundingSources={
    FundingSources(organisationId = values("organisationId"),fundingSourcesId = values("fundingSourcesId"),
      name = values("name"),costCenterNumber = values("costCenterNumber"),  date = values("date"),details = values)
  }
}
