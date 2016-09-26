package domain.common.demographics

/**
 * Created by hashcode on 2015/11/07.
 */


import play.api.libs.json.Json

case class Gender(genderId:String,name:String)

object Gender{
  implicit val genderFmt = Json.format[Gender]
}