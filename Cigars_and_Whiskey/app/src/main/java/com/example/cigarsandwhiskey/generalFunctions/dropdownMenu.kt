package com.example.cigarsandwhiskey.generalFunctions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties


// I think I have this to be able to be universal for any lists
@Composable
fun DropdownMenu(
    itemList: List<String>,
//    selectedIndex: Int, // this may be unnecessary
    initialText: String,
    onItemClick: (Int) -> Unit
){
    var showDropdown by rememberSaveable { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    var selectedText by rememberSaveable { mutableStateOf(initialText)}

    // TIPS: This is the BUTTON OBJECT, which will allow the menu to open
    Box(
        modifier = Modifier
            .background(Color.White)
            .clickable{showDropdown = !showDropdown}
            .height(40.dp)
            .width(220.dp)
            .padding(0.dp, 0.dp, 0.dp, 0.dp),
        contentAlignment = Alignment.Center
    ){
        Text(text = selectedText, modifier = Modifier.padding(5.dp), fontSize = 20.sp)
    }


    // TIPS: The Dropdown List itself
    Box{
        if (showDropdown){
            Popup(
                alignment = Alignment.TopCenter,
                properties = PopupProperties(
                    excludeFromSystemGesture = true
                ),
                onDismissRequest = {showDropdown = false}
            ) {
                Column(
                    modifier = Modifier
                        .heightIn(max = 250.dp)
                        .verticalScroll(state = scrollState)
                        .border(width = 1.dp, color = Color.Gray),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    itemList.onEachIndexed { index, item ->
                        Box(
                            modifier = Modifier
                                .background(Color.LightGray)
                                .width(320.dp)
                                .height(50.dp)
                                .clickable{
                                    selectedText = item 
                              // if this ^^^ is NOT here, the text doesn't update. IDE is wrong
                                    onItemClick(index)
                                    showDropdown = !showDropdown
                                },
                            contentAlignment = Alignment.Center
                        ){
                            Text(text = item, fontSize = 20.sp)
                        }
                    }
                }
            }
        }
    }
}

// Temporary use. Eventually, this will return a better implemented list of brands.
//  This will eventually be grabbing the list from seed data, instead of below.
//  Probably. Depends what I decide is best implementation, but I think seeding is better
fun cigarBrandsList(): List<String>{

    val brandList = listOf<String>(
        "Aganorsa Leaf", "Aging Room", "AJ Fernandez", "Alec Bradley",
        "Aladino", "Arturo Fuente", "Ashton", "Atabey",
        "AVO", "Avowed", "Bandolero", "Bellas Artes",
        "Black Label Trading Co.", "Black Works Studio",
        "Byron", "Caldwell", "Camacho", "Cavalier Genève", "C.L.E",
        "Casa 1910", "Cohiba", "Crowned Heads", "Crux", "Davidoff",
        "Diamond Crown", "Don Pepin Garcia", "Drew Estate",
        "Dunbarton Tobacco & Trust", "Eladio Diaz", "E.P. Carrillo",
        "EGM", "El Rey del Mundo", "El Septimo",
        "Espinosa", "EZRA Zion", "Felipe Gregorio", "Ferio Tego",
        "Foundation", "Fratello", "Gran Habano", "Gurkha",
        "H. Upmann", "Henry Clay", "Herrera Esteli", "Hiram & Solomon",
        "HVC", "Illusione", "Jake Wyatt Cigar Co.", "JC Newman", "JFR",
        "Joya de Nicaragua", "Kristoff", "La Aroma de Cuba", "La Barba",
        "La Flor Dominicana", "La Gloria Cubana", "La Herencia Cubana",
        "La Palina", "Leaf by Oscar", "Luciano", "Macanudo",
        "Meerapfel", "Montecristo", "My Father", "Nat Sherman",
        "New World", "Nub", "Oliva", "One Off",
        "Oveja Negra", "Padron", "Partagas", "Perdomo", "Plasencia",
        "Principle Cigars", "Punch", "Quesada",
        "Rocky Patel", "Romeo y Julieta", "RoMa Craft", "Room101",
        "San Cristobal", "San Lotano", "Santa Clara", "Serino Cigars",
        "Southern Draw", "Stolen Throne", "Tatuaje",
        "Trinidad", "Undercrown", "Vegafina",
        "Viaje", "Warped", "West Tampa Tobacco Co.",
        "Zino", "Zino Platinum", "Other"
    )

    return brandList
}

// Countries are best used as this, as the number of countries is far fewer than list of brands
fun cigarOriginList(): List<String>{

    val originList = listOf<String>(
        "Dominican Republic", "Nicaragua",
        "Honduras", "United States",
        "Cuba", "Costa Rica"
    )

    return originList
}

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

// List of whiskey brands
fun whiskeyBrandsList(): List<String>{

    val brandsList = listOf<String>(
        "Aberfeldy", "Aberlour", "Ailsa Bay", "Akashi", "Amrut", "Angel's Envy",
        "Ardbeg", "Ardmore", "Arran", "Auchentoshan", "Aultmore", "Balcones",
        "Ballantine's", "Basil Hayden's", "Belle Meade", "BenRiach", "Benromach", "Blanton's",
        "Booker's", "Bowmore", "Breckenridge", "Buffalo Trace", "Bulleit", "Bushmills",
        "Caol Ila", "Cardhu", "Chivas Regal", "E.H. Taylor", "Compass Box", "Connemara",
        "Coppercraft", "Crown Royal", "Dalmore", "Dalwhinnie", "Deanston", "Dickel",
        "Eagle Rare", "Elijah Craig", "Evan Williams", "Four Roses", "Garrison Brothers", "George Dickel",
        "GlenDronach", "Glenfiddich", "Glenfarclas", "GlenGrant", "Glenlivet", "Glenmorangie",
        "Green Spot", "Hakushu", "Hibiki", "High West", "Highland Park", "Hudson",
        "I.W. Harper", "Jack Daniel's", "Jameson", "Jefferson's", "Jim Beam", "Johnnie Walker",
        "Jura", "Kaiyo", "Kilchoman", "Knappogue Castle", "Knob Creek", "Lagavulin",
        "Laphroaig", "Larceny", "Leopold Bros.", "Little Book", "Lot No. 40", "Maker's Mark",
        "Michter's", "Midleton", "Monkey Shoulder", "Mortlach", "Nikka", "Oban",
        "Old Elk", "Old Forester", "Old Grand-Dad", "Old Overholt", "Old Pulteney", "Pendleton",
        "Penelope", "Pikesville Rye", "Pinhook", "Powers", "Rabbit Hole", "Redbreast",
        "Redemption", "Reservoir", "Russell's Reserve", "Sagamore Spirit", "Sazerac Rye", "Scapa",
        "Seagram's 7", "Smoke Wagon", "Speyburn", "Springbank", "Stagg Jr.", "Starward",
        "Stranahan's", "Suntory Toki", "Talisker", "Teeling", "Templeton Rye", "The Macallan",
        "The Sexton", "Thomas H. Handy", "Tincup", "Tullamore D.E.W.", "Uncle Nearest",
        "Westland", "WhistlePig", "Wild Turkey", "Willett", "Woodford Reserve", "Yellowstone",
        "Yamazaki"
    )

    return brandsList
}


// List of types of whiskey
fun whiskeyTypesList(): List<String>{

    val typeList = listOf<String>(
        "Bourbon",
        "Rye",
        "Wheat",
        "Single Malt",
        "Blended"
    )

    return typeList
}

// List of whiskey origins
fun whiskeyOriginList(): List<String>{

    val originList = listOf<String>(
        "Kentucky, US", "Tennessee, US", "Colorado, US",
        "Islay, Scotland", "Highland, Scotland", "Lowland, Scotland",
        "Speyside, Scotland", "Islands, Scotland", "Campbeltown ,Scotland",
        "Ireland", "Japan", "Canada"
    )

    return originList
}

// List of whiskey years aged range
fun whiskeyAgeList(): List<String>{

    val yearsList = listOf<String>(
        "3 or Less",
        "4-6",
        "7-10",
        "11-13",
        "14-16",
        "17+"
    )

    return yearsList
}





