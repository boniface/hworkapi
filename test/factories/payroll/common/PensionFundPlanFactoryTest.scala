package factories.payroll.common
import domain.payroll.common.PensionFundPlan
import org.joda.time.DateTime
import org.scalatest.FunSuite
/**
  * Created by Malu.Mukendi on 2016-09-30.
  */
class PensionFundPlanFactoryTest extends FunSuite {
  test("testPensionTypePlan")
  {
    val values = Map("pensionFundId"->"2000", "details"->"Annual")
    val dates = new DateTime
    val del: Map[String, String] = Map()
    val pensionPlan = PensionFundPlanFactory.createPensionFundPlan(values, dates)
    assert(pensionPlan == PensionFundPlan(pensionFundId= "2000", date = dates, details = del))
  }
}
