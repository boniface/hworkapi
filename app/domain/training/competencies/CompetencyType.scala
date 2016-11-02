package domain.training.competencies

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class CompetencyType(competencyTypeId: String, name: String)

object CompetencyType {
  implicit val competeFmt = Json.format[CompetencyType]
}
