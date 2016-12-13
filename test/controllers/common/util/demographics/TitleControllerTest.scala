package controllers.common.util.demographics

/**
  * Created by Aphiwe on 2016/12/12.
  */

import com.google.gson.Gson
import domain.common.demographics.Title
import domain.common.util.Mail
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

class TitleControllerTest extends PlaySpec with OneAppPerTest{
  val title = Title(
    "Mr",
    "Mister")

  "Routes" should {



    "TitleController" should {

      "Create Title Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/demographics/title/create")
          .withJsonBody(Json.toJson(title)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get Title From Controller" in {
        val request = route(app, FakeRequest(GET, "/demographics/title/")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }
}
