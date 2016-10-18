package domain.position

import java.util.Date

import play.api.libs.json.Json

/**
  * Created by hashcode on 2015/12/22.
  */
case class Position(organisationId: String,
                    positionId: String,
                    code: String,
                    title: String,
                    jobId: String,
                    positionTypeId: String,
                    description: String,
                    supervisorId: String,
                    state: String,
                    date: Date
                   )


object Position {

  implicit val positionFmt = Json.format[Position]

}
