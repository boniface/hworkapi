package domain.position

import play.api.libs.json.Json

/**
  * Created by hashcode on 2015/12/22.
  */
case class DepartureReason(organisationId: String,
                           departureReasonId: String,
                           reason: String,
                           description: String,
                           state: String)

object DepartureReason {
  implicit val departureFmt = Json.format[DepartureReason]

}
