package domain.training.courses

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
class TargetGroup(targetGroupId: String, name: String)

object TargetGroup {
  implicit val targetGroupFmt = Json.format[TargetGroup]
}
