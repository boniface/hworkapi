package factories.users

import domain.users.UserLanguage
import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
  * Created by SONY on 2016-09-28.
  */
class UserLanguageFactoryTest extends FunSuite {

  test("testCreateUserLanguage") {
    val userLanguageFactory = new UserLanguageFactory;

    val date = new DateTime(2016, 4, 2, 1, 20, 2, 0);

    val values = Map("organisationId" -> "1",
      "userId" -> "1",
      "personLanguageId" -> "1",
      "languageId" -> "1",
      "reading" -> "yes",
      "writing" -> "yes",
      "speaking" -> "yes",
      "state" -> "RSA"
    );

    val userLanguageF = userLanguageFactory.createPersonLanguage(values, date);

    assert(userLanguageF == UserLanguage(organisationId = "1",
      userId = "1",
      personLanguageId = "1",
      languageId = "1",
      reading = "yes",
      writing = "yes",
      speaking = "yes",
      state = "RSA",
      date = new DateTime(2016, 4, 2, 1, 20, 2, 0)));
  }
}
