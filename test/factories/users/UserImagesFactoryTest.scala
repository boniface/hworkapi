package factories.users

import domain.users.UserImages
import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
  * Created by SONY on 2016-09-28.
  */
class UserImagesFactoryTest extends FunSuite {

  test("testCreateUserImages") {
    val userImagesFactory = new UserImagesFactory;

    val date = new DateTime(2016, 4, 2, 1, 20, 2, 0);

    val values = Map("organisationId" -> "1",
      "userId" -> "1",
      "personImageId" -> "1",
      "url" -> "1",
      "description" -> "userimagesfactory",
      "mime" -> "testState",
      "size" -> "12"
    );

    val userImagesF = userImagesFactory.createPersonImages(values, date);

    assert(userImagesF == UserImages(organisationId = "1",
      userId = "1",
      personImageId = "1",
      url = "1",
      description = "userimagesfactory",
      mime = "testState",
      size = Option("12"),
      date = new DateTime(2016, 4, 2, 1, 20, 2, 0)));
  }
}

