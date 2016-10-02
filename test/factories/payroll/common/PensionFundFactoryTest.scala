package factories.payroll.common

import domain.payroll.common.PensionFund
import org.scalatest.FunSuite

/**
  * Created by Malu.Mukendi on 2016-09-30.
  */
class PensionFundFactoryTest extends FunSuite {
  test("testPensionFund")
  {
    val values = Map("organisationId"->"1000", "pensionFundId"->"PF2000", "name"->"Annual")
    val pensionFund = PensionFundFactory.createPensionFund(values)
    assert(pensionFund == PensionFund(organisationId ="1000", pensionFundId = "MA2000", name = "Annual"))
  }

}
