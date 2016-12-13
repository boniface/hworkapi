package services.position.Impl

import com.websudos.phantom.dsl._
import domain.position.DepartureReason
import repositories.position.DepartureReasonRepository
import services.Service
import services.position.DepartureReasonService

import scala.concurrent.Future

/**
  * Created by Theo on 22-Nov-16.
  */
class DepartureReasonServiceImpl extends DepartureReasonService with Service {
  override def createOrUpdate(departureReason: DepartureReason): Future[ResultSet] = {
    DepartureReasonRepository.save(departureReason)
  }

  override def getDepartureReasonById(organisationId: String, departureReasonId: String): Future[Option[DepartureReason]] = {
    DepartureReasonRepository.getDepartureReason(organisationId, departureReasonId)
  }

  override def getDepartureReasons(organisationId: String): Future[Seq[DepartureReason]] = {
    DepartureReasonRepository.getCompanyDepatureReasons(organisationId)
  }

}
