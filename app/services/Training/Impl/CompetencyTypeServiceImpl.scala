package services.Training.Impl

import domain.training.competencies.{CompetencyType}
import services.Service
import services.Training.CompetencyTypeService

import scala.concurrent.Future
import com.websudos.phantom.dsl._
import repositories.training.competencies.CompetencyTypeRepository

/**
 * Created by gavin.ackerman on 2016-11-12.
 */
class CompetencyTypeServiceImpl extends CompetencyTypeService with Service{
  def createOrUpdate(competency: CompetencyType): Future[ResultSet] = {
    CompetencyTypeRepository.save(competency)
  }

  def getCompetencyTypeById( id: String): Future[Option[CompetencyType]] = {
    CompetencyTypeRepository.getCompetencyTypeById( id)
  }

  def getCompetencyTypes(id: String): Future[Seq[CompetencyType]] = {
    CompetencyTypeRepository.getCompetencyTypes(id)
  }


}