package factories.payroll.funding
import domain.payroll.funding.FundingSources
import org.scalatest.FunSuite

/**
  * Created by Malu.Mukendi on 2016-09-30.
  */
class FundingSourcesFactoryTest extends FunSuite {
  test("testFundingSources")
  {
    val values = Map("organisationId"->"1000", "fundingSourcesId"->"FS2000", "name"->"Benefit", "costCenterNumber"->"costCenterNumber", "date"->"date")
    val del: Map[String, String] = Map()
    val date = new DateTime
    val del2: Map[String, String] = Map()
    val funding = FundingSourcesFactory.createFundingSources(values, date, del2)
    assert(funding == FundingSources(organisationId="1000", fundingSourcesId="FS2000", name="Benefit", costCenterNumber ="costCenterNumber", date=date, details = del2))
  }

  }
