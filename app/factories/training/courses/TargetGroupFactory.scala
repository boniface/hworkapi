package factories.training.courses

import domain.training.courses.TargetGroup

/**
  * Created by SONY on 2016-10-18.
  */
class TargetGroupFactory
{
  def createTargetGroup(values: Map[String, String]): TargetGroup=
  {
    TargetGroup(targetGroupId = values("targetGroupId"),  name = values("name"))
  }

}
