package common

import model.{Tag, MetaData}

case class SectionLink(zone: String, linkName: String, href: String, title: String, newWindow: Boolean = false)

case class Zone(name: SectionLink, sections: Seq[SectionLink])

case class NavItem(name: SectionLink, links: Seq[SectionLink] = Nil, current: Boolean = false) {
  def currentFor(metadata: MetaData) = {
    val sectionId = s"/${metadata.section}"
    sectionId == name.href || name.href == s"/${metadata.id}" || links.exists(_.href == sectionId)
  }
}


trait Sections  {

  // News
  val home = SectionLink("news", "News", "/", "Home")
  val news = SectionLink("news", "News", "/", "News")


  // UK news
  val uknews    = SectionLink("uk", "UK news", "/uk-news", "UK news")
  val politics  = SectionLink("politics", "Politics", "/politics", "Politics")
  val media     = SectionLink("media", "Media", "/media", "Media")
  val science   = SectionLink("science", "Science", "/science", "Science")
  val society   = SectionLink("society", "Society", "/society", "Society")
  val health    = SectionLink("society", "Health", "/society/health", "Health")
  val education = SectionLink("education", "Education", "/education", "Education")

  // US news
  val usnews     = SectionLink("us", "US news", "/world/usa", "United States")
  val uspolitics = SectionLink("us", "Politics", "/world/us-politics", "US politics")
  val useconomy  = SectionLink("us", "Economy", "/business/useconomy", "US economy")


  // Deprecated
  val development = SectionLink("globaldevelopment", "Global development", "/global-development", "Global development")
  val law = SectionLink("law", "Law", "/law", "Law")
  val blogs = SectionLink("blogs", "Blogs", "/tone/blog", "Blogs")
  val inpictures = SectionLink("inpictures", "Galleries", "/inpictures", "In pictures")


  // World
  val world        = SectionLink("world", "World", "/world", "World")
  val uk           = SectionLink("uk", "UK", "/uk-news", "UK")
  val us           = SectionLink("us", "US", "/world/usa", "US")
  val europe       = SectionLink("world", "Europe", "/world/europe/roundup", "Europe")
  val africa       = SectionLink("world", "Africa", "/world/africa/roundup", "Africa")
  val asia         = SectionLink("world", "Asia", "/world/asiapacific/roundup", "Asia")
  val australia    = SectionLink("world", "Australia", "/world/australia", "Australia")
  val latinamerica = SectionLink("world", "Latin America", "/world/americas/roundup", "Latin America")
  val americas     = SectionLink("world", "Americas", "/world/americas/roundup", "Americas")
  val middleeast   = SectionLink("world", "Middle East", "/world/middleeast/roundup", "Middle east")

  // Sport
  val sport  = SectionLink("sport", "Sport", "/sport", "Sport")
  val sports = sport.copy(title = "Sports")

  val football    = SectionLink("football", "Football", "/football", "Football")
  val soccer      = SectionLink("football", "Soccer", "/football", "Soccer")
  val cricket     = SectionLink("sport", "Cricket", "/sport/cricket", "Cricket")
  val tennis      = SectionLink("sport", "Tennis", "/sport/tennis", "Tennis")
  val rugbyunion  = SectionLink("sport", "Rugby Union", "/sport/rugby-union", "Rugby Union")
  val cycling     = SectionLink("sport", "Cycling", "/sport/cycling", "Cycling")
  val ussport     = SectionLink("sport", "US sport", "/sport/us-sport", "US sport")

  val nfl = SectionLink("sport", "NFL", "/sport/nfl", "NFL")
  val mlb = SectionLink("sport", "MLB", "/sport/mlb", "MLB")
  val nba = SectionLink("sport", "NBA", "/sport/nba", "NBA")
  val mls = SectionLink("football", "MLS", "/football/mls", "MLS")
  val nhl = SectionLink("sport", "NHL", "/sport/nhl", "NHL")

  // Cif
  val cif          = SectionLink("commentisfree", "Comment", "/commentisfree", "Comment")
  val cifus        = SectionLink("commentisfree", "CIF America", "/commentisfree/us-edition", "CIF America")
  val cifbelief    = SectionLink("commentisfree", "CIF belief", "/commentisfree/belief", "CIF belief")
  // Not implemented in next gen yet
  // val contributors = SectionLink("commentisfree", "Contributors", "/commentisfree/list/comment-columnists", "Contributors")
  // val cartoon      = SectionLink("commentisfree", "Cartoon", "/theguardian/series/guardiancommentcartoon", "Cartoon")

  // Contributors
  val cifglenng   = SectionLink("commentisfree", "Glenn Greenwald", "/commentisfree/series/glenn-greenwald-security-liberty", "Glenn Greenwald: On security and liberty")
  val cifanamcox  = SectionLink("commentisfree", "Ana Marie Cox", "/commentisfree/series/on-politics-and-whatever", "Ana Marie Cox: On politics... and whatever")
  val cifmichaelw = SectionLink("commentisfree", "Michael Wolff", "/commentisfree/series/michael-wolff-media-modern-life", "Michael Wolff: On media and modern life")
  val cifheidim   = SectionLink("commentisfree", "Heidi Moore", "/commentisfree/series/heidi-moore-on-business-and-economics", "Heidi Moore: On business and economics")
  val cifjillf    = SectionLink("commentisfree", "Jill Filipovic", "/commentisfree/series/on-gender-and-other-agendas", "Jill Filipovic: On gender and other agendas")
  val cifharrye   = SectionLink("commentisfree", "Harry Enten", "/commentisfree/series/harry-enten-on-polling-and-politics", "Harry Enten: On polling and politics")

  // Culture
  val culture            = SectionLink("culture", "Culture", "/culture", "Culture")
  val film               = SectionLink("culture", "Film", "/film", "Film")
  val televisionandradio = SectionLink("culture", "Television &amp; radio", "/tv-and-radio", "Television &amp; radio")
  val music              = SectionLink("culture", "Music", "/music", "Music")
  val books              = SectionLink("culture", "Books", "/books", "Books")
  val artanddesign       = SectionLink("culture", "Art &amp; design", "/artanddesign", "Art & design")
  val theatre            = SectionLink("culture", "Theatre", "/stage", "Theatre")
  val theater            = theatre.copy(linkName = "Theater", title = "Theater")

  // Life and style
  val lifeandstyle  = SectionLink("lifeandstyle", "Life & Style", "/lifeandstyle", "Life & style")
  val foodanddrink  = SectionLink("lifeandstyle", "Food &amp; drink", "/lifeandstyle/food-and-drink", "Food &amp; drink")
  val fashion       = SectionLink("lifeandstyle", "Fashion", "/fashion", "Fashion")
  val homes         = SectionLink("lifeandstyle", "Homes", "/lifeandstyle/homes", "Homes")
  val women         = SectionLink("lifeandstyle", "Women", "/lifeandstyle/women", "Women")
  val loveandfamily = SectionLink("lifeandstyle", "Love &amp; family", "/lifeandstyle/family", "Love &amp; family")
  val wellbeing     = SectionLink("lifeandstyle", "Wellbeing", "/lifeandstyle/health-and-wellbeing", "Wellbeing")
  // US Life and style
  val celebrity     = SectionLink("lifeandstyle", "Celebrity", "/lifeandstyle/celebrity", "Celebrity")
  val family        = SectionLink("lifeandstyle", "Love &amp; family", "/lifeandstyle/family", "Love &amp; family")
  val wellness      = wellbeing.copy(linkName = "Wellness", title = "Wellness")
  val fashionandbeauty = SectionLink("lifeandstyle", "Fashion &amp; beauty", "/fashion", "Fashion &amp; beauty")
  val relationships = SectionLink("lifeandstyle", "Relationships", "/lifeandstyle/relationships", "Relationships")

  // Technology
  val technology = SectionLink("technology", "Tech", "/technology", "Technology")
  val games      = SectionLink("technology", "Games", "/technology/games", "Games")
  val mobiles    = SectionLink("technology", "Mobiles &amp; tablets", "/technology/mobiles", "Mobiles &amp; tablets")
  val apps       = SectionLink("technology", "Apps", "/technology/apps", "Apps")
  val apple      = SectionLink("technology", "Apple", "/technology/apple", "Apple")
  val google     = SectionLink("technology", "Google", "/technology/google", "Google")
  // US Technology
  val computing  = SectionLink("technology", "Computing", "/technology/computing", "Computing")

  // Business
  // Instead of recession,
  // we should link to /business/series/eurozone-crisis-live, yet to be implemented
  val business  = SectionLink("business", "Business", "/business", "Business")
  val economics = SectionLink("business", "Economics", "/business/economics", "Economics")
  val eurozonecrisis = SectionLink("business", "Eurozone crisis", "/business/debt-crisis", "Eurozone crisis")
  // val euro      = SectionLink("business", "Euro", "/business/euro", "Euro") // Not implemented in next gen yet
  val banking   = SectionLink("business", "Banking", "/business/banking", "Banking")
  val retail    = SectionLink("business", "Retail", "/business/investing", "Retail")
  val opinion   = SectionLink("business", "Opinion", "/business/blog", "Opinion")
  // US Business
  val economy      = SectionLink("business", "Economy", "/business/useconomy", "Economy")
  val eurozone  = SectionLink("business", "Eurozone", "/business/debt-crisis", "Eurozone")
  val worldeconomy = SectionLink("business", "World Economy", "/business/economics", "World Economy")

  // Money
  val money          = SectionLink("money", "Money", "/money", "Money")
  val consumer       = SectionLink("money", "Consumer", "/money/consumer-affairs", "Consumer affairs")
  val property       = SectionLink("money", "Property", "/money/property", "Property")
  val workandcareers = SectionLink("money", "Work", "/money/work-and-careers", "Work &amp; careers")
  val familyfinances = SectionLink("money", "Family finances", "/money/family-finances", "Family finances")
  val saving         = SectionLink("money", "Savings", "/money/savings", "Savings")
  val borrowing      = SectionLink("money", "Borrowing", "/money/debt", "Borrowing")
  // US Money
  val personalfinance = SectionLink("money", "Personal finance", "/money/personal-finance", "US personal finance")

  // Travel
  val travel             = SectionLink("travel", "Travel", "/travel", "Travel")
  val shortbreaks        = SectionLink("travel", "Short breaks", "/travel/short-breaks", "Short breaks")
  val top10s             = SectionLink("travel", "Top 10s", "/travel/top10", "Top 10s")
  val uktravel           = SectionLink("travel", "UK", "/travel/uk", "United Kingdom")
  val europetravel       = SectionLink("travel", "Europe", "/travel/europe", "Europe")
  val hotels             = SectionLink("travel", "Hotels", "/travel/hotels", "Hotels")
  val travelfoodanddrink = SectionLink("travel", "Food &amp; drink", "/travel/travelfoodanddrink", "Food &amp; drink")
  // US Travel
  val ustravel           = SectionLink("travel", "US", "/travel/usa", "United States")
  val toptens            = top10s.copy(linkName = "Top tens", title = "Top tens")
  val barsandrestaurants = SectionLink("travel", "Bars &amp; restaurants", "/travel/bars+restaurants", "Bars &amp; restaurants")

  // Environment
  val environment        = SectionLink("environment", "Environment", "/environment", "Environment")
  val climatechange      = SectionLink("environment", "Climate change", "/environment/climate-change", "environment")
  val wildlife           = SectionLink("environment", "Wildlife", "/environment/wildlife", "wildlife")
  val energy             = SectionLink("environment", "Energy", "/environment/energy", "Energy")
  val conservation       = SectionLink("environment", "Conservation", "/environment/conservation", "Conservation")
  val food               = SectionLink("environment", "Food", "/environment/food", "Food")
  val environmentopinion = SectionLink("environment", "Opinion", "/environment/blog", "Opinion")
  // US Environment
  val sustainability     = SectionLink("environment", "Sustainability", "/environment/sustainable-development", "Sustainability")

  def footballNav(metaData: MetaData) = metaData match {
    case tag: Tag if tag.isFootballTeam => NavItem(football, Seq(
      SectionLink("football", "Live scores", "/football/live", "Live scores"),
      SectionLink("football", "Teams", "/football/teams", "Teams"), // Should be "Clubs"
      SectionLink("football", "Competitions", "/football/competitions", "Competitions"),
      SectionLink("football", "Results", s"/football/${tag.url}/results", "Results"),
      SectionLink("football", "Fixtures", s"/football/${tag.url}/fixtures", "Fixtures"),
      SectionLink("football", "Tables", "/football/tables", "Tables")
    ))
    case tag: Tag if tag.isFootballCompetition => NavItem(football, Seq(
      SectionLink("football", "Live scores", s"/football/${tag.url}/live", "Live scores"),
      SectionLink("football", "Teams", "/football/teams", "Teams"), // Should be "Clubs"
      SectionLink("football", "Competitions", "/football/competitions", "Competitions"),
      SectionLink("football", "Results", s"/football/${tag.url}/results", "Results"),
      SectionLink("football", "Fixtures", s"/football/${tag.url}/fixtures", "Fixtures"),
      SectionLink("football", "Tables", s"/football/${tag.url}/tables", "Tables")
    ))
    case _ => NavItem(football, Seq(
      SectionLink("football", "Live scores", "/football/live", "Live scores"),
      SectionLink("football", "Teams", "/football/teams", "Teams"), // Should be "Clubs"
      SectionLink("football", "Competitions", "/football/competitions", "Competitions"),
      SectionLink("football", "Results", "/football/results", "Results"),
      SectionLink("football", "Fixtures", "/football/fixtures", "Fixtures"),
      SectionLink("football", "Tables", "/football/tables", "Tables")
    ))
  }
}

trait Zones extends Sections {

  val ukZone = Zone(uknews,
    Seq(politics, media, science, society, health, education)
  )

  val usZone = Zone(uknews,
    Seq(uspolitics, useconomy, science, media)
  )

  val worldZone = Zone(world,
    Seq(us, europe, africa, asia, australia, americas)
  )

  val cifZone = Zone(cif,
    Seq(cifus, cifbelief)
  )

  val sportZone = Zone(sport,
    Seq(football, cricket, tennis, rugbyunion, cycling, ussport)
  )

  val sportsZone = Zone(sport,
    Seq(soccer, mls, nfl, mlb, nba)
  )

  val lifeandstyleZone = Zone(lifeandstyle,
    Seq(foodanddrink, fashion, homes, women, loveandfamily, wellbeing)
  )

  val cultureZone = Zone(culture,
    Seq(film, televisionandradio, music, books, artanddesign, theatre)
  )

  val businessZone = Zone(business,
    Seq(economics, eurozonecrisis, banking, retail, opinion)
  )

  val moneyZone = Zone(money,
    Seq(consumer, property, workandcareers, saving, borrowing)
  )

  val travelZone = Zone(travel,
    Seq(shortbreaks, top10s, uktravel, europetravel, hotels, travelfoodanddrink)
  )

  val technologyZone = Zone(technology,
    Seq(games, mobiles, apps, apple, google)
  )

  val environmentZone = Zone(environment,
    Seq(climatechange, wildlife, energy, conservation, food, environmentopinion)
  )

}
