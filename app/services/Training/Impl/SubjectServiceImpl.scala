package services.Training.Impl
import domain.training.courses.Subject
import domain.training.competencies.Evaluation
import repositories.Training.competencies.EvaluationRepository
import repositories.Training.courses.SubjectRepository
import services.Service
import services.Training.{SubjectService, EvaluationService}
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-13.
 */
class SubjectServiceImpl extends SubjectService with Service{
  def createOrUpdate(subject: Subject): Future[ResultSet] = {
    SubjectRepository.save(subject)
  }

  def getSubjectById( id: String): Future[Option[Subject]] = {
    SubjectRepository.getSubjectById( id)
  }

  def getSubject(): Future[Seq[Subject]] = {
    SubjectRepository.getAllSubject
  }


}
