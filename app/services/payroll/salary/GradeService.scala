package app.services.payroll.salary

import app.services.payroll.salary.Impl.GradeServiceImpl
import domain.payroll.salary.Grade
import com.websudos.phantom.dsl._
import scala.concurrent.Future

/**
  * Created by Malu.Mukendi on 2016-11-11.
  */
trait GradeService {

  def createOrUpdate(grade:Grade):Future[ResultSet]

  def getGradeById(organisationId: String, gradeId: String): Future[Option[Grade]]

  def getCompanyGrades(organisationId: String): Future[Seq[Grade]]
}
object GradeService{
  def apply: GradeService = new GradeServiceImpl()
}