package domain.organisations

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/05.
  *
 */
case class CompanyDocuments(
                             company: String,
                             id: String,
                             description: String,
                             url: String,
                             mime: String,
                             date: Date,
                             permission: Set[String],
                             state:String)

object CompanyDocuments {
  implicit val companyDocFmt = Json.format[CompanyDocuments]
}
