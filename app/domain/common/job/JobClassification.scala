package domain.common.job

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/11/04.
 */
case class JobClassification(id: String,
                             currentTitle: String,
                             oldTitle: String,
                             oldCode: String,
                             currentCode: String,
                             codeConversion: String,
                             comment: String,
                             state:String
                              )

object JobClassification {
  implicit val jbFmt = Json.format[JobClassification]

}
