package controllers.common.util.training

import com.google.gson.Gson
import domain.training.courses.Criteria
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.Helpers._
import com.google.gson.Gson
import play.api.test.Helpers._
import play.api.test._
/**
 * Created by gavin.ackerman on 2016-12-09.
 */
class CriteriaControllerTest extends PlaySpec with OneAppPerTest {
  //val gson = new Gson()
  val criteria = Criteria(
    "criteriaId",
    "name"
 )

  "Routes" should {



    "CriteriaController" should {

      "Create Criteria Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/training/criteria/create")
          .withJsonBody(Json.toJson(criteria)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get Mail From Controller" in {
        val request = route(app, FakeRequest(GET, "/training/criteria/criteriaId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
