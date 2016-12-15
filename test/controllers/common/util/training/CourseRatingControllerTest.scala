package controllers.common.util.training

import com.google.gson.Gson
import domain.common.util.Mail
import domain.training.schedules.CourseRating
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.test.Helpers._
import play.api.test._
import play.api.libs.json.Json
/**
 * Created by gavin.ackerman on 2016-12-09.
 */
class CourseRatingControllerTest extends PlaySpec with OneAppPerTest {
 // val gson = new Gson()
  val courseRating = CourseRating(
    "organisationId",
    "scheduledCourseId",
    3,
    "comment"
)

  "Routes" should {



    "CourseRatingController" should {

      " Create CourseRating Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/training/courserating/create")
          .withJsonBody(Json.toJson(courseRating)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get CourseRating From Controller" in {
        val request = route(app, FakeRequest(GET, "/training/courserating/organisationId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
