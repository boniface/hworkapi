package domain.common.util

import play.api.libs.json.Json


case class ApiKeys(id: String, value: String)

object ApiKeys {
  implicit val apiKeysFmt = Json.format[ApiKeys]
}
