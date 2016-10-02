package factories.payroll.common
import domain.payroll.common.Benefit
import org.scalatest.FunSuite
/**
  * Created by Malu.Mukendi on 2016-09-30.
  */
class BenefitFactoryTest extends FunSuite {
  test("testCreateBenefit")
  {
    val values = Map("organisationId"->"1000", "benefitId"->"2000", "name"->"Benefit", "date"->"2016/05/20", "state"->"Pending")
    val benefit = BenefitFactory.createBenefit(values)
    assert(benefit == Benefit(organisationId="1000", benefitId="2000", name="Benefit",date="2016/05/20", state="Pending"))
  }
}
