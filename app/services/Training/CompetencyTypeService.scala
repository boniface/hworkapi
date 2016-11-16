package services.Training

import domain.job.Job
import domain.training.competencies.{CompetencyType, Competency}
import services.Training.Impl.CompetencyTypeServiceImpl
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-09.
 */
trait CompetencyTypeService {
  def createOrUpdate(competencyType: CompetencyType): Future[ResultSet]

  def getCompetencyTypeById( id: String): Future[Option[CompetencyType]]

  def getCompetencyTypes(id: String): Future[Seq[CompetencyType]]

}

object CompetencyTypeService{
  def apply: CompetencyTypeService = new CompetencyTypeServiceImpl()
}
