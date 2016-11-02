package factories.common.util

import domain.common.util.Status

/**
  * Created by SONY on 2016-09-28.
  */
class StatusFactory {
  def createStatus(values:Map[String,String]):Status={

    Status(statusId = values("statusId"),name = values("name"),value = values("value"))
  }

}
