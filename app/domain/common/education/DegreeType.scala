package domain.common.education

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/12/13.
 */
case class DegreeType(id: String, name: String, state: String)

object DegreeType {
  implicit val evalFmt = Json.format[DegreeType]
}