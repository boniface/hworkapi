package factories.training.competency



import domain.training.competencies.Competency



/**
 * Created by gavin.ackerman on 2016-10-23.
 */
 object CompetencyFactory {
  def createCompetency(values:Map[String, String]):Competency={
    Competency(compencyId = values("compencyId"), name = values("name"), competencyTypeId = values("competencyTypeId"))
  }
}
