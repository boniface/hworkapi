package factories.users

import domain.users.PersonImages
import org.joda.time.DateTime

/**
  * Created by SONY on 2016-09-27.
  */
class PersonImagesFactory
{
  def createPersonImages(values: Map[String, String], date:DateTime ):PersonImages=
  {
    PersonImages(organisationId = values("organisationId"), userId = values("userId"), personImageId = values("personImageId"),
      url = values("url"), description = values("description"), mime = values("mime"), size = Option("size"), date = date)
  }

}
