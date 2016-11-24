package services.payroll.common

import com.websudos.phantom.dsl.ResultSet
import domain.payroll.common.PensionFund
import services.payroll.common.Impl.PensionFundServiceImpl

import scala.concurrent.Future

/**
  * Created by Malu.Mukendi on 2016-11-19.
  */
trait PensionFundService {

  def createOrUpdate(pensionFund:PensionFund):Future[ResultSet]

  def getFileResultById(organisationId: String, pensionFundId: String): Future[Option[PensionFund]]

  def getPensionFund(organisationId: String): Future[Seq[PensionFund]]
}
object PensionFundService{
  def apply: PensionFundService = new PensionFundServiceImpl()
}