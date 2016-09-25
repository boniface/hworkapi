package domain.organisations

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/03.
 */
case class CompanyLogo(company: String,
                       id: String,
                       url: String,
                       size: Option[String],
                       description:String,
                       mime: String,
                       date: Date)

object CompanyLogo {

  implicit val companyLogoFmt = Json.format[CompanyLogo]

}
