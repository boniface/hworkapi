package factories.payroll.salary

import domain.payroll.salary.Grade
import org.joda.time.DateTime

/**
  * Created by SONY on 2016-10-18.
  */
class GradeFactory
{
  def createGrade(values: Map[String, String], date: DateTime, numberOfNotches: Int, valDec: Map[String,BigDecimal]): Grade=
    Grade(organisationId = values("organisationId"), gradeId = values("gradeId"),  name = values("name"),
      numberOfNotches = numberOfNotches, lowerAmount = valDec("lowerAmount"), topAmount = valDec("topAmount"),
      currencyId = values("currencyId"), date = date, notes = values("notes"))

}
