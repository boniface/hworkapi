package conf.util

import javax.inject.Inject

import com.google.inject.Singleton
import play.api.http.HttpFilters
import play.filters.cors.CORSFilter
/**
  * Created by hashcode on 2016/03/09.
  */
@Singleton
class HashFilters @Inject() (corsFilter: CORSFilter) extends HttpFilters {
  def filters = Seq(corsFilter)
}
