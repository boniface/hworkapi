package domain.organisations
import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2015/10/29.
  */
case class Organisation(organisationId: String,
                        name: String,
                        details: Map[String, String],
                        adminattached: String,
                        date: DateTime,
                        state: String)

object Organisation {
  implicit val companyFmt = Json.format[Organisation]


}
