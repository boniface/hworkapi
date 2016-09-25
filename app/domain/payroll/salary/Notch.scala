package domain.payroll.salary

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/01/23.
  */
case class Notch(gradeId:String, id:String, name:String, amount: BigDecimal)

object Notch{
  implicit val notchFmt= Json.format[Notch]

}
