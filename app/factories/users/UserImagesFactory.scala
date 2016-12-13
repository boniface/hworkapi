package factories.users

import domain.users.UserImages
import org.joda.time.DateTime

/**
  * Created by SONY on 2016-09-27.
  */
class UserImagesFactory
{
  def createPersonImages(values: Map[String, String], date:DateTime ):UserImages=
  {
    UserImages(organisationId = values("organisationId"), userId = values("userId"), personImageId = values("personImageId"),
      url = values("url"), description = values("description"), mime = values("mime"), size = Option("size"), date = date)
  }

}
