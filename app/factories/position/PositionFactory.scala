package factories.position

import domain.position.Position
import org.joda.time.DateTime

/**
  * Created by hashcode on 2016/09/25.
  * Edited by Yusiry
  */
class PositionFactory {
  def createPosition(values:Map[String,String], date:DateTime):Position={
    Position(positionId = values("positionId"),
      organisationId = values("organisationId"),
      code = values("code"),
      title = values("title"),
      jobId = values("jobId"),
      positionTypeId = values("positionTypeId"),
      description = values("description"),
      supervisorId = values("supervisorId"),
      state = values("state"),
      date = DateTime)
  }
}
