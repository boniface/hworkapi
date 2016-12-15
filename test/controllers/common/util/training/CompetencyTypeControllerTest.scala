package controllers.common.util.training

import com.google.gson.Gson
import domain.common.util.Mail
import domain.training.competencies.CompetencyType
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test._
import play.api.libs.json.Json

/**
 * Created by gavin.ackerman on 2016-12-09.
 */
class CompetencyTypeControllerTest extends PlaySpec with OneAppPerTest {
 // val gson = new Gson()
  val competency = CompetencyType(
    "competencyTypeId",
    "name"
 )

  "Routes" should {



    "CompetencyTypeController" should {

      " Create CompetencyType Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/training/competencytype/create")
          .withJsonBody(Json.toJson(competency)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get CompetencyType From Controller" in {
       val request = route(app, FakeRequest(GET, "/training/competencytype/competencyTypeId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
