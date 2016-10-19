package domain.common.util


import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/11/28.
 */
case class Mail( mailId:String,
                 key:String,
                 value:String,
                 host:String,
                 port:String,
                 state:String,
                 date:DateTime)

object Mail{
  implicit val mailFmt = Json.format[Mail]


}
