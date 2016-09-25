package domain.common.demographics

/**
 * Created by hashcode on 2015/11/07.
 */
import play.api.libs.json.Json

case class Race(id:String,name:String,state:String)

object Race{
  implicit val raceFmt = Json.format[Race]
}