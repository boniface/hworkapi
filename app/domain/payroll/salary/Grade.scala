package domain.payroll.salary

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/08.
 */
case class Grade(
                  company:String,
                  id:String,
                  name:String,
                  numberOfNotches:Int,
                  lowerAmount:BigDecimal,
                  topAmount:BigDecimal,
                  currencyId:String,
                  date:Date,
                  notes:String
                  )

object Grade{
  implicit val gradeFmt = Json.format[Grade]
}
