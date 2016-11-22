package app.services.payroll.salary

import app.services.payroll.salary.Impl.NotchServiceImpl
import domain.payroll.salary.Notch
import com.websudos.phantom.dsl._
import scala.concurrent.Future

/**
  * Created by Malu.Mukendi on 2016-11-11.
  */
trait NotchService {

  def createOrUpdate(notch:Notch):Future[ResultSet]

  def getNotchById(gradeId: String, id: String): Future[Option[Notch]]

  def getGradeNotches(gradeId: String): Future[Seq[Notch]]
}
object NotchService{
  def apply: NotchService = new NotchServiceImpl()
}
