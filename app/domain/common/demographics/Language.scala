package domain.common.demographics

/**
 * Created by hashcode on 2015/11/07.
 */
import play.api.libs.json.Json

case class Language(languageId:String,name:String)

object Language{
  implicit val langFmt = Json.format[Language]
}
