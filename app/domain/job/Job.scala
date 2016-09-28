package domain.job

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/07.
 */
case class Job(organisationId:String,
               jobId:String,
               jobClassificationId:String,
               title:String,
               code:String,
               description:String,
               date:DateTime,
               state:String
                )

object Job {

  implicit val jobFmt = Json.format[Job]

}
