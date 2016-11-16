package services.Training.Impl

import domain.training.competencies.{CompetencyType, Competency}
import repositories.Training.competencies.CompetencyTypeRepository

import services.Service
import services.Training.CompetencyTypeService
import scala.concurrent.Future
import com.websudos.phantom.dsl._

/**
 * Created by gavin.ackerman on 2016-11-12.
 */
class CompetencyTypeServiceImpl extends CompetencyTypeService with Service{
  def createOrUpdate(competency: CompetencyType): Future[ResultSet] = {
    CompetencyTypeRepository.save(competency)
  }

  def getCompetencyTypeById( id: String): Future[Option[Competency]] = {
    CompetencyTypeRepository.getCompetencyTypeById( id)
  }

  def getCompetencyType(): Future[Seq[Competency]] = {
    CompetencyTypeRepository.getAllCompetencyType
  }


}