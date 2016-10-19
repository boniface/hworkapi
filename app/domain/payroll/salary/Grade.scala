package domain.payroll.salary

import java.util.Date

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/01/08.
  */
case class Grade(organisationId: String,
                 gradeId: String,
                 name: String,
                 numberOfNotches: Int,
                 lowerAmount: BigDecimal,
                 topAmount: BigDecimal,
                 currencyId: String,
                 date: DateTime,
                 notes: String)

object Grade {
  implicit val gradeFmt = Json.format[Grade]
}
