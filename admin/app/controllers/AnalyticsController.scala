package controllers

import play.api.mvc.Controller
import common.Logging
import tools._


object AnalyticsController extends Controller with Logging with AuthLogging {
  def kpis() = AuthAction { request =>
  // thats right, we only do PROD analytics
    Ok(views.html.kpis("PROD", Seq(
      AveragePageviewsByDayGraph,
      ReturnUsersByDayGraph
    )))
  }

  def pageviews() = AuthAction { request =>
      // thats right, we only do PROD analytics
      Ok(views.html.pageviews("PROD", Seq(
        PageviewsGeoGraph,
        PageviewsGraph,
        NewPageviewsGraph,
        PageviewsBrowsersTreeMapGraph,
        PageviewsOSTreeMapGraph
      )))
  }
}
