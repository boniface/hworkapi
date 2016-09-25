package domain.common.util

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/11/28.
 */
case class Mail(
                 id:String,
                 key:String,
                 value:String,
                 host:String,
                 port:String,
                 state:String,
                 date:Date)

object Mail{
  implicit val mailFmt = Json.format[Mail]


}
