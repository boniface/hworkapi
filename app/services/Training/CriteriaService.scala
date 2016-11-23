package services.Training

import domain.training.courses.Criteria
import services.Training.Impl.CriteriaServiceImpl

import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-10.
 */
trait CriteriaService {
  def createOrUpdate(course: Criteria): Future[ResultSet]

  def getCriteriaById( id: String): Future[Option[Criteria]]

  def getCriteria(id: String): Future[Seq[Criteria]]

}

object CriteriaService{
  def apply: CriteriaService = new CriteriaServiceImpl()
}