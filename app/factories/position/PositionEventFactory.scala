package factories.position

import domain.position.PositionEvent
import org.joda.time.DateTime

/**
  * Created by theo on 2016/10/06.
  */
class PositionEventFactory {
  def createPositionEvent(values:Map[String, String], date:DateTime):PositionEvent={
    PositionEvent(positionId = values("positionId"),
      positionEventId = values("positionEventId"),
      date = date,
      event = values("event"))
  }

}
