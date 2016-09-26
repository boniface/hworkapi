package domain.common.demographics

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/10/30.
 */
case class Role(roleId: String, name: String, description: String)

object Role {
  implicit val roleFmt = Json.format[Role]
}
