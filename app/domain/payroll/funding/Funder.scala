package domain.payroll.funding

import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/08.
 */
case class Funder(
                   company:String,
                   id:String,
                   name:String,
                   costCenterNumber:String,
                   date:String,
                   details:Map[String,String])

object Funder{

  implicit val funderFmt = Json.format[Funder]
}
