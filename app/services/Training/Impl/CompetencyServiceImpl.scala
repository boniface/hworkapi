package services.Training.Impl

import domain.job.Job
import domain.training.competencies.Competency
import repositories.Training.competencies.CompetencyRepository
import repositories.job.JobRepository
import services.Service
import services.Training.CompetencyService
import services.job.JobService
import scala.concurrent.Future
import com.websudos.phantom.dsl._
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

  def getCompetency(): Future[Seq[Competency]] = {
    CompetencyRepository.getAllcomp
  }


}