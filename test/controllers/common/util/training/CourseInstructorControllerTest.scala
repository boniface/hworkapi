package controllers.common.util.training

import com.google.gson.Gson
import domain.common.util.Mail
import domain.training.schedules.CourseInstructors
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.test.Helpers._
import play.api.test._
import play.api.libs.json.Json
/**
 * Created by gavin.ackerman on 2016-12-09.
 */
class CourseInstructorControllerTest extends PlaySpec with OneAppPerTest {
 // val gson = new Gson()
  val courseInstructors = CourseInstructors(
    "scheduledCourseId",
    "TrainingInstructorId"
 )

  "Routes" should {



    "CourseFundingController" should {

      " Create CourseInstructors Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/training/coursefunding/create")
          .withJsonBody(Json.toJson(courseInstructors)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get CourseFunding From Controller" in {
        val request = route(app, FakeRequest(GET, "/training/coursefunding/scheduledCourseId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
