package services.Training.Impl

import domain.training.competencies.Competency

import services.Service
import services.Training.CompetencyService

import scala.concurrent.Future
import com.websudos.phantom.dsl._
import repositories.training.competencies.CompetencyRepository
/**
 * Created by gavin.ackerman on 2016-11-10.
 */
class CompetencyServiceImpl extends CompetencyService with Service{
  def createOrUpdate(competency: Competency): Future[ResultSet] = {
    CompetencyRepository.save(competency)
  }

  def getCompetencyById( id: String): Future[Option[Competency]] = {
    CompetencyRepository.getcompById( id)
  }

  def getCompetencys(id: String): Future[Seq[Competency]] = {
    CompetencyRepository.getCompetencys(id)
  }


}