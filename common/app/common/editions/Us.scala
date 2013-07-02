package common.editions

import common._
import org.joda.time.DateTimeZone
import model.{MetaData, ItemTrailblockDescription}
import views.support.{Headline, Thumbnail, Featured}
import scala.Some
import common.NavItem
import contentapi.QueryDefaults

object Us extends Edition(
  id = "US",
  displayName = "US edition",
  timezone = DateTimeZone.forID("America/New_York"),
  hreflang = "en-us") with Sections with Zones with QueryDefaults {

  implicit val US = Us
  val zones = Seq(
    usZone,
    worldZone,
    cifZone,
    sportsZone,
    cultureZone,
    lifeandstyleZone,
    businessZone,
    travelZone,
    technologyZone,
    environmentZone
  )

  def navigation(metadata: MetaData) = Seq(
    NavItem(home),
    NavItem(us, Seq(uspolitics, useconomy, science, media)),
    NavItem(world, Seq(uk, europe, middleeast, asia, latinamerica, africa)),
    NavItem(cif, Seq(cifglenng, cifanamcox, cifmichaelw, cifheidim, cifjillf, cifharrye)),
    NavItem(sports, Seq(soccer, mls, nfl, mlb, nba)),
    NavItem(culture, Seq(film, televisionandradio, books, artanddesign, music, theater)),
    NavItem(lifeandstyle, Seq(celebrity, foodanddrink, wellness, family, fashionandbeauty, relationships)),
    NavItem(business, Seq(economy, worldeconomy, eurozone, personalfinance)),
    NavItem(travel, Seq(ustravel, uktravel, toptens, hotels, barsandrestaurants)),
    NavItem(technology, Seq(games, mobiles, apps, computing)),
    NavItem(environment, Seq(climatechange, energy, sustainability, wildlife)),
    footballNav(metadata)
  )


  val configuredFronts = Map(
    Editionalise("", Us) -> Seq(
      ItemTrailblockDescription("", "News", numItemsVisible = 5, style = Some(Featured), showMore = true),
      ItemTrailblockDescription("sport", "Sports", numItemsVisible = 5, style = Some(Featured), showMore = true),
      ItemTrailblockDescription("commentisfree", "Comment is free", numItemsVisible = 3, style = Some(Featured), showMore = true),
      ItemTrailblockDescription("culture", "Culture", numItemsVisible = 3, style = Some(Thumbnail), showMore = true),
      ItemTrailblockDescription("business", "Business", numItemsVisible = 1, style = Some(Thumbnail)),
      ItemTrailblockDescription("lifeandstyle", "Life and style", numItemsVisible = 1, style = Some(Thumbnail)),
      ItemTrailblockDescription("technology", "Technology", numItemsVisible = 1, style = Some(Thumbnail)),
      ItemTrailblockDescription("travel", "Travel", numItemsVisible = 1, style = Some(Thumbnail))
    ),

    Editionalise("sport", Us) -> Seq(
      ItemTrailblockDescription("sport", "Sports", numItemsVisible = 5, style = Some(Featured), showMore = true),
      ItemTrailblockDescription("sport/nfl", "NFL", numItemsVisible = 3, style = Some(Featured)),
      ItemTrailblockDescription("sport/mlb", "MLB", numItemsVisible = 1, style = Some(Thumbnail)),
      ItemTrailblockDescription("sport/nba", "NBA", numItemsVisible = 1, style = Some(Thumbnail)),
      ItemTrailblockDescription("football/mls", "MLS", numItemsVisible = 1, style = Some(Thumbnail)),
      ItemTrailblockDescription("sport/nhl", "NHL", numItemsVisible = 1, style = Some(Thumbnail))
    ),

  Editionalise("culture", Us) -> Seq(
      ItemTrailblockDescription("culture", "Culture", numItemsVisible = 5, style = Some(Featured), showMore = true),
      ItemTrailblockDescription("film", "Film", numItemsVisible = 1, style = Some(Thumbnail)),
      ItemTrailblockDescription("music", "Music", numItemsVisible = 1, style = Some(Thumbnail)),
      ItemTrailblockDescription("stage", "Stage", numItemsVisible = 1, style = Some(Thumbnail)),
      ItemTrailblockDescription("books", "Books", numItemsVisible = 1, style = Some(Headline)),
      ItemTrailblockDescription("artanddesign", "Art & Design", numItemsVisible = 1, style = Some(Headline)),
      ItemTrailblockDescription("technology/games", "Games", numItemsVisible = 1, style = Some(Headline)),
      ItemTrailblockDescription("tv-and-radio", "TV & Radio", numItemsVisible = 1, style = Some(Thumbnail))
    ),

  Editionalise("australia", Us)  -> Seq(
      ItemTrailblockDescription("", "News", numItemsVisible = 8, style = Some(Featured), showMore = false)(Au),
      ItemTrailblockDescription("sport", "Sport", numItemsVisible = 3, style = Some(Featured), showMore = false)(Au),
      ItemTrailblockDescription("sport/australia-sport", "Australia sport", numItemsVisible = 3, style = Some(Thumbnail), showMore = false)(Au),
      Au.cultureCustomBlock,
      Au.commentCustomBlock,
      ItemTrailblockDescription("lifeandstyle", "Life and style", numItemsVisible = 1, style = Some(Thumbnail), showMore = false),
      ItemTrailblockDescription("technology", "Technology", numItemsVisible = 1, style = Some(Thumbnail), showMore = false)(Au),
      ItemTrailblockDescription("science", "Science", numItemsVisible = 1, style = Some(Thumbnail), showMore = false),
      ItemTrailblockDescription("environment", "Environment", numItemsVisible = 1, style = Some(Thumbnail), showMore = false),
      Au.videoCustomBlock
    )
  )
}
