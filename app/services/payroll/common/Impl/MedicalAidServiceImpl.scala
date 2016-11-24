package app.services.payroll.common.Impl
import com.websudos.phantom.dsl._
import app.services.payroll.common.MedicalAidService
import domain.payroll.common.MedicalAid
import repositories.payroll.common.MedicalAidRepository
import services.Service

import scala.concurrent.Future

/**
  * Created by Malu.Mukendi on 2016-11-11.
  */
class MedicalAidServiceImpl extends MedicalAidService with Service {
  override def createOrUpdate(medicalAid: MedicalAid): Future[ResultSet] = {
    MedicalAidRepository.save(medicalAid)
  }
  override def getFileResultById(organisationId: String, medicalAidId: String): Future[Option[MedicalAid]] = {
    MedicalAidRepository.getFileResultById(organisationId, medicalAidId)
  }

  override def getMedicalAid(organisationId: String): Future[Seq[MedicalAid]] = {
    MedicalAidRepository.getMedicalAid(organisationId)
  }
}
