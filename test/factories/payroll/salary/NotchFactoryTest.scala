package factories.payroll.salary
import domain.payroll.salary.Notch
import org.scalatest.FunSuite

/**
  * Created by Malu.Mukendi on 2016-09-30.
  */
class NotchFactoryTest extends FunSuite {
  test("testNotch")
  {
    val values = Map("organisationId"->"1000", "benefitId"->"B2000", "name"->"Annual")
    val values2:BigDecimal = BigDecimal(2000)
    val notch = NotchFactory.createNotch(values, values2)
    assert(notch == Notch(gradeId ="1000", id ="B2000", name="Annual", amount = values2 ))
  }
}
