package domain.organisations

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/10/29.
 */
case class Company(
                    id: String,
                    name: String,
                    details:Map[String,String],
                    adminattached:String,
                    date:Date,
                    state:String
                    )

object Company {
  implicit val companyFmt = Json.format[Company]



}
