package factories.payroll.salary
import java.util.Date
import domain.payroll.salary.Grade


/**
  * Created by Malu.Mukendi on 2016-09-30.
  */
class GradeFactoryTest extends org.scalatest.FunSuite {
  test("testCreateGrade")
  {
    val values = Map("organisationId"->"1000", "gradeId"->"2000", "name"->"Benefit", "currencyId"->"currencyId", "notes"->"notes")
    val dates = new Date
    val del: Map[String, String] = Map()
    val lower:BigDecimal = BigDecimal(2000)
    val amount:BigDecimal = BigDecimal(5000)
    val num:Int = 20
    val grade = GradeFactory.createGrade(values, num, lower, amount, dates)
    assert(grade == Grade(organisationId="1000", gradeId="2000", name="Benefit",numberOfNotches = 20,
      lowerAmount = lower, topAmount = amount, currencyId = "currencyId", date = dates, notes = "notes"))
  }
}
