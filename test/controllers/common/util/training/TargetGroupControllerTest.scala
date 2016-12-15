package controllers.common.util.training


import domain.training.courses.TargetGroup
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
class TargetGroupControllerTest extends PlaySpec with OneAppPerTest {
  //val gson = new Gson()
  val targetGroup = TargetGroup(
    "targetGroupId",
    "name")

  "Routes" should {



    "TargetGroupController" should {

      "Create TargetGroup Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/training/targetgroup/create")
          .withJsonBody(Json.toJson(targetGroup)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get TargetGroup From Controller" in {
        val request = route(app, FakeRequest(GET, "/training/targetgroup/targetGroupId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
