package domain.common.util

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/06/07.
 */
case class Token(id: String, tokenValue: String)

object Token {
  implicit val tokenFmt = Json.format[Token]

}
