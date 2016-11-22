package factories.payroll.salary

import domain.payroll.salary.Notch

/**
  * Created by SONY on 2016-10-18.
  */
class NotchFactory
{
  def createNotch(values: Map[String, String], valDec: Map[String, BigDecimal]): Notch=
    Notch(gradeId = values("gradeId"), id = values("id"), name= values("name"), amount= valDec("amount")
    )

}
