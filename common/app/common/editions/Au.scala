package common.editions

import org.joda.time.DateTimeZone
import model._
import common._
import views.support.{Headline, Thumbnail, Featured}
import scala.concurrent.Future
import conf.ContentApi
import contentapi.QueryDefaults
import com.gu.openplatform.contentapi.model.ItemResponse
import common.NavItem


//This object exists to be used with ItemTrailblockDescription and is not a real edition like the others.
//All that is really being used is Edition.id, which is AU
//It is not included in the Edition.all sequence
object Au extends Edition(
  id = "AU",
  displayName = "Australia edition",
  DateTimeZone.forID("Australia/Sydney"),
  hreflang = "en-au") with Sections with Zones with QueryDefaults {

  implicit val AU = Au

  val cultureCustomBlock = CustomTrailblockDescription("culture", "Culture", numItemsVisible = 3, style = Some(Thumbnail)){

    val promiseOfCulture: Future[ItemResponse] = ContentApi.item.itemId("culture")
      .edition("au")
      .showTags("all")
      .showFields(trailFields)
      .showInlineElements(inlineElements)
      .showMedia("all")
      .showReferences(references)
      .showStoryPackage(true)
      .tag(s"-stage/stage,-artanddesign/art,-stage/theatre,-stage/dance,-stage/comedy,-stage/musicals,-artanddesign/photography,($supportedTypes)")
      .response

    EditorsPicsOrLeadContentAndLatest(promiseOfCulture)
  }

  val commentCustomBlock = CustomTrailblockDescription("commentisfree", "Comment is free", numItemsVisible = 3, style = Some(Featured)){

    val promiseOfComment: Future[ItemResponse] = ContentApi.item.itemId("commentisfree")
      .edition("au")
      .showTags("all")
      .showFields(trailFields)
      .showInlineElements(inlineElements)
      .showMedia("all")
      .showReferences(references)
      .showStoryPackage(true)
      .tag(s"world/australia,($supportedTypes)")
      .response

    EditorsPicsOrLeadContentAndLatest(promiseOfComment)
  }

  val videoCustomBlock = CustomTrailblockDescription("type/video", "Video", numItemsVisible = 1, style = Some(Featured)){

    val promiseOfAustralianVideo: Future[ItemResponse] = ContentApi.item.itemId("type/video")
      .edition("au")
      .showTags("all")
      .showFields(trailFields)
      .showInlineElements(inlineElements)
      .showMedia("all")
      .showReferences(references)
      .showStoryPackage(true)
      .tag(s"world/australia")
      .response

    promiseOfAustralianVideo.map(_.results.map(new Content(_)))
  }


  val zones = Seq(
    newsZone,
    sportZone,
    cifZone,
    cultureZone,
    technologyZone,
    businessZone,
    moneyZone,
    lifeandstyleZone,
    travelZone
  )

  def navigation(metadata: MetaData) = Seq(
    NavItem(home),
    NavItem(uk, Seq(politics, media, science, society, health, education)),
    NavItem(world, Seq(us, europe, middleeast, asiapacific, africa, americas)),
    NavItem(cif, Seq(cifbelief, cifgreen)),
    NavItem(sport, Seq(football, cricket, tennis, rugbyunion, cycling)),
    footballNav(metadata),
    NavItem(lifeandstyle, Seq(foodanddrink, fashion, relationships, healthandwellbeing, women)),
    NavItem(culture, Seq(film, music, books, televisionandradio, artanddesign, stage)),
    NavItem(business, Seq(economics, banking, property, workandcareers, savings)),
    NavItem(travel, Seq(shortbreaks, uktravel, europetravel, hotels, resturants)),
    NavItem(technology, Seq(internet, games, mobilephones, appsblog)),
    NavItem(environment, Seq(climatechange, wildlife, energy, conservation, food))
  )

  val configuredFronts = Map(
    Editionalise("", Au) -> Seq(
      ItemTrailblockDescription("", "News", numItemsVisible = 8, style = Some(Featured), showMore = false),
      ItemTrailblockDescription("sport", "Sport", numItemsVisible = 3, style = Some(Featured), showMore = false),
      ItemTrailblockDescription("sport/australia-sport", "Australia sport", numItemsVisible = 3, style = Some(Thumbnail), showMore = false),
      Au.cultureCustomBlock,
      Au.commentCustomBlock,
      ItemTrailblockDescription("lifeandstyle", "Life and style", numItemsVisible = 1, style = Some(Thumbnail), showMore = false),
      ItemTrailblockDescription("technology", "Technology", numItemsVisible = 1, style = Some(Thumbnail), showMore = false),
      ItemTrailblockDescription("science", "Science", numItemsVisible = 1, style = Some(Thumbnail), showMore = false),
      ItemTrailblockDescription("environment", "Environment", numItemsVisible = 1, style = Some(Thumbnail), showMore = false),
      Au.videoCustomBlock
    ),

    Editionalise("sport", Au) -> Seq(
      ItemTrailblockDescription("sport", "Sport", numItemsVisible = 5, style = Some(Featured), showMore = true),
      ItemTrailblockDescription("football", "Football", numItemsVisible = 3, style = Some(Featured), showMore = true),
      ItemTrailblockDescription("sport/cricket", "Cricket", numItemsVisible = 1, style = Some(Thumbnail)),
      ItemTrailblockDescription("sport/rugby-union", "Rugby Union", numItemsVisible = 1, style = Some(Thumbnail)),
      ItemTrailblockDescription("sport/motorsports", "Motor Sport", numItemsVisible = 1, style = Some(Thumbnail)),
      ItemTrailblockDescription("sport/tennis", "Tennis", numItemsVisible = 1, style = Some(Thumbnail)),
      ItemTrailblockDescription("sport/golf", "Golf", numItemsVisible = 1, style = Some(Thumbnail)),
      ItemTrailblockDescription("sport/horse-racing", "Horse Racing", numItemsVisible = 1, style = Some(Headline)),
      ItemTrailblockDescription("sport/rugbyleague", "Rugby League", numItemsVisible = 1, style = Some(Headline)),
      ItemTrailblockDescription("sport/us-sport", "US Sport", numItemsVisible = 1, style = Some(Headline)),
      ItemTrailblockDescription("sport/boxing", "Boxing", numItemsVisible = 1, style = Some(Headline)),
      ItemTrailblockDescription("sport/cycling", "Cycling", numItemsVisible = 1, style = Some(Headline))
    ),

    Editionalise("culture", Au) -> Seq(
      ItemTrailblockDescription("culture", "Culture", numItemsVisible = 5, style = Some(Featured), showMore = true),
      ItemTrailblockDescription("tv-and-radio", "TV & Radio", numItemsVisible = 1, style = Some(Thumbnail)),
      ItemTrailblockDescription("film", "Film", numItemsVisible = 1, style = Some(Thumbnail)),
      ItemTrailblockDescription("music", "Music", numItemsVisible = 1, style = Some(Thumbnail)),
      ItemTrailblockDescription("stage", "Stage", numItemsVisible = 1, style = Some(Thumbnail)),
      ItemTrailblockDescription("books", "Books", numItemsVisible = 1, style = Some(Headline)),
      ItemTrailblockDescription("artanddesign", "Art & Design", numItemsVisible = 1, style = Some(Headline)),
      ItemTrailblockDescription("technology/games", "Games", numItemsVisible = 1, style = Some(Headline))
    ),

    Editionalise("commentisfree", Au) -> Seq(
      ItemTrailblockDescription("commentisfree", "Comment is free", numItemsVisible = 20, style = Some(Featured))
    ),

    Editionalise("business", Au) -> Seq(
      ItemTrailblockDescription("business", "Business", numItemsVisible = 20, style = Some(Featured))
    ),

    Editionalise("money", Au) -> Seq(
      ItemTrailblockDescription("money", "Money", numItemsVisible = 20, style = Some(Featured))
    ),

    Editionalise("australia", Au) -> Seq(
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

  val configuredFrontsFacia = Map(
    (Editionalise("", Au), Seq(
      RunningOrderTrailblockDescription("news", "top-stories", "Top Stories", 5),
      RunningOrderTrailblockDescription("news", "features", "Features", 5),
      RunningOrderTrailblockDescription("news", "editors-picks", "Editor's Picks", 5)
    )),

    (Editionalise("culture", Au), Seq(
      RunningOrderTrailblockDescription("culture", "top-stories", "Top Stories", 5),
      RunningOrderTrailblockDescription("culture", "features", "Features", 5),
      RunningOrderTrailblockDescription("culture", "editors-picks", "Editor's Picks", 5)
    )),

    (Editionalise("fashion", Au), Seq(
      RunningOrderTrailblockDescription("fashion", "top-stories", "Top Stories", 5),
      RunningOrderTrailblockDescription("fashion", "features", "Features", 5),
      RunningOrderTrailblockDescription("fashion", "editors-picks", "Editor's Picks", 5)
    )),

    (Editionalise("technology", Au), Seq(
      RunningOrderTrailblockDescription("technology", "top-stories", "Top Stories", 5),
      RunningOrderTrailblockDescription("technology", "features", "Features", 5),
      RunningOrderTrailblockDescription("technology", "editors-picks", "Editor's Picks", 5)
    )),

    (Editionalise("film", Au), Seq(
      RunningOrderTrailblockDescription("film", "top-stories", "Top Stories", 5),
      RunningOrderTrailblockDescription("film", "features", "Features", 5),
      RunningOrderTrailblockDescription("film", "editors-picks", "Editor's Picks", 5)
    ))
  )
}
