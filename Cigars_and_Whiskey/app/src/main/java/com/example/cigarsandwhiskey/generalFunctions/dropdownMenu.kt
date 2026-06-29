package com.example.cigarsandwhiskey.generalFunctions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties


// I think I have this to be able to be universal for any lists
@Composable
fun DropdownMenu(
    itemList: List<String>,
    initialText: String,
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit
){
    var showDropdown by rememberSaveable { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    var selectedText by rememberSaveable { mutableStateOf(initialText)}

    Box(modifier = modifier) {

        // TIPS: This is the BUTTON OBJECT, which will allow the menu to open
        Box(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(4.dp))
                .clickable{ showDropdown = !showDropdown}
                .fillMaxWidth()
                .height(45.dp)
                .border(3.dp, Color.Black)
                .onSizeChanged{it.width}
                .padding(0.dp, 0.dp, 0.dp, 0.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = selectedText, modifier = Modifier.padding(5.dp),
                fontSize = (LocalConfiguration.current.screenWidthDp * 0.043f).sp
            )
        }


        // TIPS: The Dropdown List itself
        Box {
            if (showDropdown) {
                Popup(
                    properties = PopupProperties(
                        excludeFromSystemGesture = true
                    ),
                    onDismissRequest = { showDropdown = false },
                    offset = IntOffset(0, 130)
                ) {
                    Column(
                        modifier = Modifier
                            .heightIn(max = 250.dp)
                            .clip(RoundedCornerShape(25.dp))
                            .verticalScroll(state = scrollState)
                            .alpha(.99f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        itemList.onEachIndexed { index, item ->
                            Box(
                                modifier = Modifier
                                    .background(Color.LightGray)
                                    .width(320.dp)
                                    .height(50.dp)
                                    .clickable {
                                        selectedText = item
                                        // if this ^^^ is NOT here, the text doesn't update. IDE is wrong
                                        onItemClick(index)
                                        showDropdown = !showDropdown
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = item, fontSize = 20.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

// Temporary use. Eventually, this will return a better implemented list of brands.
//  This will eventually be grabbing the list from seed data, instead of below.
//  Probably. Depends what I decide is the best implementation, but I think seeding is better
fun cigarBrandsList(): List<String>{

    val brandList = listOf<String>(
        "Aganorsa Leaf", "ADVentura", "Aging Room", "AJ Fernandez", "Alec Bradley",
        "Aladino", "American Viking", "Arturo Fuente", "Ashton", "Asylum", "Atabey", "AVO",
        "Avowed", "BAMF", "Bandolero", "Black Bird", "Black Label Trading Co.", "Black Works Studio",
        "Brick House", "Byron", "Caldwell", "Camacho", "Cavalier Genève", "C.L.E", "CAO",
        "Casa 1910", "Cohiba", "Crowned Heads", "Crux", "Dapper", "Davidoff",
        "Diamond Crown", "Diesel", "Don Pepin Garcia", "Drew Estate",
        "Dunbarton Tobacco & Trust", "Eladio Diaz", "E.P. Carrillo",
        "EGM", "El Rey del Mundo", "El Septimo", "El Titan de Bronze",
        "Espinosa", "Esteban Carreras", "EZRA Zion", "Felipe Gregorio", "Ferio Tego",
        "Fortaleza Y Libre", "Foundation", "Fratello", "Gran Habano", "Gurkha",
        "H. Upmann", "Henry Clay", "Herrera Esteli", "Hiram & Solomon", "Hoyo de Monterrey",
        "HVC", "Illusione", "Indian Motorcycle", "Jake Wyatt Cigar Co.", "JC Newman", "JFR",
        "Joya de Nicaragua", "Kristoff", "La Aroma de Cuba", "La Barba",
        "La Flor Dominicana", "La Gloria Cubana", "La Herencia Cubana",
        "La Palina", "Leaf by Oscar", "Liga Privada", "Luciano", "Lure Cigars", "Macanudo",
        "Meerapfel", "Micallef", "Montecristo", "My Father", "Nat Sherman",
        "New World", "Nub", "Oliva", "Oveja Negra", "Oz Family", "Padron", "Partagas",
        "Perdomo", "Plasencia", "Principle Cigars", "Punch", "Quesada", "Red Anchor",
        "Rocky Patel", "Rojas", "Romeo y Julieta", "RoMa Craft", "Room101",
        "San Cristobal", "San Lotano", "Sanj Patel", "Santa Clara", "Serino Cigars", "Silent Hero",
        "Southern Draw", "Stolen Throne", "Tatuaje", "Trinidad", "Undercrown", "United Cigars",
        "Vegafina", "Viaje", "Warfighter", "Warped", "West Tampa Tobacco Co.",
        "Zino", "Zino Platinum", "Other"
    )

    return brandList
}

// Countries are best used as this, as the number of countries is far fewer than list of brands
fun cigarOriginList(): List<String>{

    val originList = listOf<String>(
        "Dominican Republic", "Nicaragua",
        "Honduras", "United States",
        "Cuba", "Costa Rica", "Undisclosed"
    )

    return originList
}

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

// List of whiskey brands
fun whiskeyBrandsList(): List<String>{

    val brandsList = listOf<String>(
        "13th Century", "13th Colony","Aberfeldy", "Aberlour", "Ailsa Bay", "Akashi", "Amrut", "Angel's Envy",
        "Ardbeg", "Ardmore", "Arran", "Auchentoshan", "Aultmore", "Balcones", "Ballantine's", "Balvenie",
        "Barrell Craft Spirits", "Barrel House", "Basil Hayden's", "Belle Meade", "Benn Holladay", "BenRiach",
        "Benromach", "Big House", "Blanton's", "Booker's", "Bowman Brothers", "Bowmore", "Bradshaw", "Breckenridge", "Bruichladdich",
        "Buffalo Trace", "Bulleit", "Bushmills", "Caol Ila", "Cardhu", "Caribou Crossing", "Castle & Key", "Chivas Regal",
        "Clyde Mays", "Compass Box", "Connemara", "Coppercraft", "Crown Royal", "Dalmore", "Dalwhinnie", "Dark Arts",
        "Deanston", "Dickel", "Dubliner", "Eagle Rare", "E.H. Taylor", "Elijah Craig", "Elmer T. Lee", "Evan Williams",
        "Four Roses", "Frey Ranch", "Garrison Brothers", "George Dickel", "GlenDronach", "Glenfiddich", "Glenfarclas", 
        "GlenGrant", "Glenlivet", "Glenmorangie", "Glenrothes","Glen Scotia", "Green River", "Green Spot", "Hakushu",
        "Hazelburn", "Hibiki", "High West", "Highland Park", "Hudson", "I.W. Harper", "Jack Daniel's", "Jameson", "Jefferson's",
        "Jim Beam", "Johnnie Walker", "Jura", "Kaiyo", "Kilchoman", "Kilkerran", "Knappogue Castle", "Knob Creek", "Lagavulin",
        "Lagg", "Laphroaig", "Larceny", "Ledaig", "Leopold Bros.", "Little Book", "Longrow", "Lot No. 40",
        "Maker's Mark", "Michter's", "Midleton", "Monkey Shoulder", "Mortlach", "New Riff", "Nikka", "Nulu", "Oban",
        "Old Elk", "Old Fitzgerald", "Old Forester", "Old Grand-Dad", "Old Overholt", "Old Pepper", "Old Pulteney",
        "Peerless", "Pendleton", "Penelope", "Pikesville Rye", "Pinhook", "Powers", "Rabbit Hole", "Redbreast",
        "Redemption", "Reservoir", "Rockhill Farms", "Russell's Reserve", "Sagamore Spirit", "Sazerac Rye",
        "Scapa", "Seagram's 7", "Smoke Wagon", "Speyburn", "Springbank", "Stagg Jr.", "Starward",
        "Stranahan's", "Still Austin", "Suntory Toki", "Talisker", "Teeling", "Templeton Rye", "The Macallan",
        "The Sexton", "Thomas H. Handy", "Tincup", "Tobermory", "Traveller", "Tullamore D.E.W.", "Uncle Nearest",
        "Weller", "Westland", "Whiskey War", "WhistlePig", "Wild Turkey", "Willett", "Woodford Reserve",
        "Yellowstone", "Yamazaki"
    )

    return brandsList
}


// List of types of whiskey
fun whiskeyTypesList(): List<String>{

    val typeList = listOf<String>(
        "Bourbon",
        "Rye",
        "Wheat",
        "Tennessee",
        "American",
        "U.S. Single Malt",
        "Single Malt Scotch",
        "Blended",
        "Blended Scotch",
        "Canadian"
    )

    return typeList
}

// List of whiskey origins
fun whiskeyOriginList(): List<String>{

    val originList = listOf<String>(
        "Kentucky, US", "Tennessee, US", "Colorado, US", "Indiana, US",
        "Texas, US", "Virginia, US", "Islay, Scotland", "Highland, Scotland",
        "Lowland, Scotland", "Speyside, Scotland", "Islands, Scotland",
        "Campbeltown ,Scotland", "Ireland", "Japan", "Canada"
    )

    return originList
}

// List of whiskey years aged range
fun whiskeyAgeList(): List<String>{

    val yearsList = listOf<String>(
        "NAS",
        "3 or Less",
        "4-6",
        "7-10",
        "11-13",
        "14-16",
        "17+"
    )

    return yearsList
}