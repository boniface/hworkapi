package factories.common.education

import domain.common.education.EducationType

class EducationTypeFactory {
  def createEducationType(values:Map[String, String]):EducationType={
 EducationType(educationTypeId = values("educationTypeId"),name = values("name"))
  }

}