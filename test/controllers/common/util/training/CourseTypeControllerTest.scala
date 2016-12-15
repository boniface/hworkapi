package controllers.common.util.training


import domain.training.courses.CourseType
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import com.google.gson.Gson
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._
/**
 * Created by gavin.ackerman on 2016-12-09.
 */
class CourseTypeControllerTest extends PlaySpec with OneAppPerTest {
//  val gson = new Gson()
  val courseType = CourseType(
    "courseTypeId",
    "name"
 )

  "Routes" should {



    "CourseTypeController" should {

      "Create CourseType Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/training/coursetype/create")
          .withJsonBody(Json.toJson(courseType)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get CourseType From Controller" in {
        val request = route(app, FakeRequest(GET, "/training/coursetype/courseTypeId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
