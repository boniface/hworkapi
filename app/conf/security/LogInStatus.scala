package conf.security

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/06.
  */
case class LogInStatus(status:String)

object LogInStatus{
  implicit val personFmt = Json.format[LogInStatus]

}
