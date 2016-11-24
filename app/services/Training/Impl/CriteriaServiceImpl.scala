package services.Training.Impl

import domain.training.courses.Criteria

import services.Service
import services.Training.{CriteriaService}

import scala.concurrent.Future
import com.websudos.phantom.dsl._
import repositories.training.courses.CriteriaRepository
/**
 * Created by gavin.ackerman on 2016-11-13.
 */
class CriteriaServiceImpl extends CriteriaService with Service{
  def createOrUpdate(criteria: Criteria): Future[ResultSet] = {
    CriteriaRepository.save(criteria)
  }
  def getCriteriaById( id: String): Future[Option[Criteria]] = {
    CriteriaRepository.getCriteriaById(id)
  }

  def getCriteria(id: String): Future[Seq[Criteria]] = {
    CriteriaRepository.getCriteria(id)
  }
}