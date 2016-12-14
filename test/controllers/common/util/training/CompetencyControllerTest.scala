package controllers.common.util.training

import com.google.gson.Gson
import domain.common.util.Mail
import domain.training.competencies.Competency
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.api.test._
/**
 * Created by gavin.ackerman on 2016-12-09.
 */
class CompetencyControllerTest extends PlaySpec with OneAppPerTest {
 // val gson = new Gson()
  val competency = Competency(
    "compencyId",
    "name",
    "competencyTypeId"
)

  "Routes" should {



    "CompetencyController" should {

      " Create Competency Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/training/competency/create")
          .withJsonBody(Json.toJson(competency)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get Competency From Controller" in {
        val request = route(app, FakeRequest(GET, "/training/competency/compencyId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
