package app.services.payroll.salary.Impl

import app.services.payroll.salary.GradeService
import domain.payroll.salary.Grade
import repositories.payroll.salary.GradeRepository
import services.Service
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
  * Created by Malu.Mukendi on 2016-11-11.
  */
class GradeServiceImpl extends GradeService with Service{
  override def createOrUpdate(grade: Grade): Future[ResultSet] = {
    GradeRepository.save(grade)
  }

  override def getGradeById(organisationId: String, gradeId: String): Future[Option[Grade]] = {
    GradeRepository.getGradeById(organisationId, gradeId)
  }

  override def getCompanyGrades(organisationId: String): Future[Seq[Grade]] = {
    GradeRepository.getCompanyGrades(organisationId)
  }
}
