package factories.training.competency



import domain.training.competencies.CompetencyType

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
object CompetencyTypeFactory {
  def createCompetencyType(values:Map[String, String]):CompetencyType={
    CompetencyType(competencyTypeId = values("competencyTypeId"), name = values("name"))
  }
}