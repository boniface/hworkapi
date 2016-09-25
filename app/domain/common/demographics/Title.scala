package domain.common.demographics

/**
 * Created by hashcode on 2015/11/07.
 */

import play.api.libs.json.Json

case class Title(id: String, name: String,state:String)

object Title {
  implicit val titleFmt = Json.format[Title]
}
