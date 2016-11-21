package factories.training.courses

import domain.training.courses.TargetGroup

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
class TargetGroupFactory {
  def createTargetGroup(values:Map[String, String]):TargetGroup={
    TargetGroup(targetGroupId = values("targetGroupId"), name = values("name"))
  }
}
