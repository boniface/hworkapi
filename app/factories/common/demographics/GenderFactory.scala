package factories.common.demographics

import domain.common.demographics.Gender

class GenderFactory {
  def createGender(values:Map[String, String]):Gender={
 Gender(genderId = values("genderId"),name = values("name"))
  }

}