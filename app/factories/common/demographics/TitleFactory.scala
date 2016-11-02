package factories.common.demographics

import domain.common.demographics.Title

class TitleFactory {
  def createTitle(values:Map[String, String]):Title={
 Title(titleId = values("TitleId"),name = values("name"))
  }

}