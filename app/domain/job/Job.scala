package domain.job

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/07.
 */
case class Job(
              company:String,
              id:String,
              jobClassificationId:String,
              title:String,
              code:String,
              description:String,
              date:Date,
              state:String
                )

object Job {

  implicit val jobFmt = Json.format[Job]

}
