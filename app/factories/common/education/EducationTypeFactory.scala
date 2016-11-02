package factories.common.education

import domain.common.education.EducationType

/**
  * Created by SONY on 2016-09-28.
  */
class EducationTypeFactory {
  def createEducationType(values:Map[String,String]):EducationType={

    EducationType(educationTypeId = values("dducationTypeId"),name = values("name"))
  }

}
