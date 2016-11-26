package domain.common.util

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/08/28.
  */
case class Credential(email:String, password:String)

object Credential{
  implicit val credentialsFmt = Json.format[Credential]
}
