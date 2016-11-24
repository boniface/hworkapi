package app.services.payroll.common

import app.services.payroll.common.Impl.MedicalAidServiceImpl
import domain.payroll.common.MedicalAid
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
  * Created by Malu.Mukendi on 2016-11-11.
  */
trait MedicalAidService {

  def createOrUpdate(medicalAid:MedicalAid):Future[ResultSet]

  def getFileResultById(organisationId: String, medicalAidId: String): Future[Option[MedicalAid]]

  def getMedicalAid(organisationId: String): Future[Seq[MedicalAid]]
}
object MedicalAidService{
  def apply: MedicalAidService = new MedicalAidServiceImpl()
}