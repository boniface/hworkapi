package services.Training

import services.Training.Impl.EvaluationServiceImpl

import scala.concurrent.Future
import com.websudos.phantom.dsl._
import domain.training.competencies.{Evaluation, Competency}

/**
 * Created by gavin.ackerman on 2016-11-09.
 */
trait EvaluationService {
  def createOrUpdate(evaluation: Evaluation): Future[ResultSet]

  def getEvaluationById( id: String): Future[Option[Evaluation]]

  def getEvaluations(id: String): Future[Seq[Evaluation]]

}

object EvaluationService{
  def apply: EvaluationService = new EvaluationServiceImpl()
}