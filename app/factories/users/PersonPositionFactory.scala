package factories.users

import java.util.Date

import domain.users.PersonPosition
import org.joda.time.DateTime

/**
  * Created by SONY on 2016-09-27.
  */
class PersonPositionFactory
{
  def createPersonPosition(values: Map[String, String], date:DateTime):PersonPosition=
  {
    PersonPosition(organisationId = values("organisationId"), userId = values("userId"), personPositionId = values("personPositionId"),
      statusDate = date, positionId = values("positionId"), statusId = values("statusId"), reason = values("reason"))
  }

}
