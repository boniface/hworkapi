package controllers.common.util.training

import com.google.gson.Gson
import domain.common.util.Mail
import domain.training.courses.CourseCategory
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._
/**
 * Created by gavin.ackerman on 2016-12-09.
 */
class CourseCategoryControllerTest extends PlaySpec with OneAppPerTest {
 // val gson = new Gson()
  val courseCategory = CourseCategory(
    "organisationId",
    "courseCategoryId",
    "name"
)

  "Routes" should {



    "CourseCategoryController" should {

      " Create CourseCategory Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/training/coursecategory/create")
          .withJsonBody(Json.toJson(courseCategory)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get CourseCategory From Controller" in {
        val request = route(app, FakeRequest(GET, "/training/coursecategory/organisationId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
