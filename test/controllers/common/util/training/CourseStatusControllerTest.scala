package controllers.common.util.training

import com.google.gson.Gson
import domain.common.util.Mail
import domain.training.courses.CourseStatus
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.test.Helpers._
import org.joda.time.DateTime
import play.api.test.Helpers._
import play.api.test._
import play.api.libs.json.Json
/**
 * Created by gavin.ackerman on 2016-12-09.
 */
class CourseStatusControllerTest extends PlaySpec with OneAppPerTest {
  //val gson = new Gson()
  val courseStatus = CourseStatus(
    "courseId",
    "status",
    new DateTime("2004-12-13T21:39:45.618-08:00"))

  "Routes" should {


    "CourseStatusController" should {

     " Create CourseStatus Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/training/coursestatus/create")
        .withJsonBody(Json.toJson(courseStatus)))
        .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
        }

      "Get Mail From Controller" in {
        val request = route(app, FakeRequest(GET, "/training/coursestatus/courseId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }
}
