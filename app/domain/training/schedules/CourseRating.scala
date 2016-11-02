package domain.training.schedules

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class CourseRating(organisationId:String,
                        scheduledCourseId:String,
                        rating:Int,
                        comment:String)

object CourseRating{
  implicit val courseRatingFmt =Json.format[CourseRating]

}
