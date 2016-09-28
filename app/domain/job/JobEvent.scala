package domain.job

import java.util.Date

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/07.
 */
case class JobEvent(jobId:String,
                    jobEventId:String,
                    date:DateTime,
                    event:String)

object JobEvent{
  implicit val jobeventFmt = Json.format[JobEvent]

}
