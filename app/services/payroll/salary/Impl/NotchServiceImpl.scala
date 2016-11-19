package app.services.payroll.salary.Impl

import app.services.payroll.salary.NotchService
import domain.payroll.salary.Notch
import repositories.payroll.salary.NotchRepository
import services.Service
import com.websudos.phantom.dsl._

import scala.concurrent.Future

/**
  * Created by Malu.Mukendi on 2016-11-11.
  */
class NotchServiceImpl extends NotchService with Service{
  override def createOrUpdate(notch: Notch): Future[ResultSet] = {
    NotchRepository.save(notch)
  }

  override def getNotchById(gradeId: String, id: String): Future[Option[Notch]] = {
    NotchRepository.getNotchById(gradeId, id)
  }

  override def getGradeNotches(gradeId: String): Future[Seq[Notch]] = {
    NotchRepository.getGradeNotches(gradeId)
  }
}
