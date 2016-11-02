package domain.common.education

import play.api.libs.json.Json

case class EducationType(educationTypeId: String, name: String)

object EducationType {
  implicit val eduFmt = Json.format[EducationType]
}