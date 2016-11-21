package services.Training

import domain.job.Job
import domain.training.competencies.Competency
import services.Training.Impl.CompetencyServiceImpl
import services.job.Impl.JobServiceImpl
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-09.
 */
trait CompetencyService {
  def createOrUpdate(competency: Competency): Future[ResultSet]

  def getCompetencyById( id: String): Future[Option[Competency]]

  def getCompetencys(): Future[Seq[Competency]]

}

object CompetencyService{
  def apply: CompetencyService = new CompetencyServiceImpl()
}