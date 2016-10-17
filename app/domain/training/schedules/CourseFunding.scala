package domain.training.schedules

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class CourseFunding(scheduledCourseId: String,
                         fundingSourcesId:String,
                         amount:BigDecimal,
                         currencyId:String)
object CourseFunding{
  implicit val courseFundingFmt = Json.format[CourseFunding]

}
