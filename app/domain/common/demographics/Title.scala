package domain.common.demographics

/**
 * Created by hashcode on 2015/11/07.
 */

import play.api.libs.json.Json

case class Title(titleId: String, name: String)

object Title {
  implicit val titleFmt = Json.format[Title]
}
