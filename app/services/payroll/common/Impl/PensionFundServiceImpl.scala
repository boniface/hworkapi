package services.payroll.common.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.payroll.common.PensionFund
import repositories.payroll.common.PensionFundRepository
import services.Service
import services.payroll.common.PensionFundService
import scala.concurrent.Future

/**
  * Created by Malu.Mukendi on 2016-11-19.
  */
class PensionFundServiceImpl extends PensionFundService with Service{
  override def createOrUpdate(pensionFund: PensionFund): Future[ResultSet] = {
    PensionFundRepository.save(pensionFund)
  }

  override def getFileResultById(organisationId: String, pensionFundId: String): Future[Option[PensionFund]] = {
    PensionFundRepository.getFileResultById(organisationId, pensionFundId)
  }

  override def getPensionFund(organisationId: String): Future[Seq[PensionFund]] = {
    PensionFundRepository.getPensionFund(organisationId)
  }
}
