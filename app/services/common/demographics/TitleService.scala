package services.common.demographics

import com.websudos.phantom.dsl._
import domain.common.demographics.Title
import services.common.demographics.Impl.TitleServiceImpl

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/10/27.
  */
trait TitleService {

  def createOrUpdate(title:Title):Future[ResultSet]

  def getTitleById(title:String):Future[Option[Title]]

  def getTitles(title:String):Future[Seq[Title]]

}
object TitleService{
  def apply: TitleService = new TitleServiceImpl()
}