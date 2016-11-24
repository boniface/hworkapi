package services.position

import com.websudos.phantom.dsl._
import domain.position.DepartureReason
import services.position.Impl.DepartureReasonServiceImpl

import scala.concurrent.Future

/**
  * Created by Theo on 22-Nov-16.
  */
trait DepartureReasonService {
  def createOrUpdate(departureReason: DepartureReason): Future[ResultSet]

  def getDepartureReasonById(organisationId: String, departureReasonId: String): Future[Option[DepartureReason]]

  def getDepartureReasons(organisationId: String): Future[Seq[DepartureReason]]
}

  object DepartureReasonService{
    def apply: DepartureReasonService = new DepartureReasonServiceImpl()
}
