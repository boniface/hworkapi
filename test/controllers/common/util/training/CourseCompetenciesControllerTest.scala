package controllers.common.util.training

import com.google.gson.Gson
import domain.common.util.Mail
import domain.training.courses.CourseCompetencies
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._
/**
 * Created by gavin.ackerman on 2016-12-09.
 */
class CourseCompetenciesControllerTest extends PlaySpec with OneAppPerTest {
  val gson = new Gson()
  val courseCompetencies = CourseCompetencies(
    "organisationId",
    "courseId",
    "compentencyId"
  )

  "Routes" should {



    "CourseCompetenciesController" should {

      " Create CourseCompetencies Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/training/coursecompetencies/create")
          .withJsonBody(Json.toJson(courseCompetencies)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get CourseCompetencies From Controller" in {
        val request = route(app, FakeRequest(GET, "/training/coursecompetencies/organisationId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
