package factories.payroll.salary
import java.util.Date

import domain.payroll.salary.Grade
/**
  * Created by Malu.Mukendi on 2016-09-30.
  */
object GradeFactory {
  def createGrade(values:Map[String, String], num:Int, lower:BigDecimal, amt:BigDecimal, dates:Date):Grade={
    Grade(organisationId = values("organisationId"), gradeId = values("gradeId"), name = values("name"), numberOfNotches = num,
      lowerAmount = lower, topAmount = amt, currencyId = values("currencyId"), date = dates, notes = values("notes"))
  }
}
