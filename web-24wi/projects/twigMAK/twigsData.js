



Acer_macrophyllum={ 
    scientificName:["Acer macrophyllum"],
    CommonName:["Big-leaf maple"], 
    arrangement:["opposite"]
};

Alnus_rubra={ 
    scientificName:["Alnus rubra"],
    CommonName:["red alder"], 
    arrangement:["alternate"] 
};

Cornus_nuttallii={ 
    scientificName:["Cornus nuttallii"],
    CommonName:["Pacific dogwood"], 
    arrangement:["opposite"] 
};

Frangula_purshiana={ 
    scientificName:["Frangula purshiana"],
    CommonName:["cascara sagrada"], 
    arrangement:["alternate"] 
};

Fraxinus_latifolia={ 
    scientificName:["Fraxinus latifolia"],
    CommonName:["Oregon ash"], 
    arrangement:["opposite"] 
};

Malus_fusca={ 
    scientificName:["Malus fusca"],
    CommonName:["crabapple"], 
    arrangement:["alternate"] 
};

Populus_tremuloides={ 
    scientificName:["Populus tremuloides"],
    CommonName:["quaking aspen"], 
    arrangement:["alternate"] 
};

Populus_trichocarpa={ 
    scientificName:["Populus trichocarpa"],
    CommonName:["black cottonwood"], 
    arrangement:["alternate"] 
};

Prunus_emarginata={ 
    scientificName:["Prunus emarginata"],
    CommonName:["bitter cherry"], 
    arrangement:["alternate"] 
};

Quercus_garryana={ 
    scientificName:["Quercus garryana"],
    CommonName:["Oregon white oak"], 
    arrangement:["alternate"] 
};

Salix_spp={ 
    scientificName:["Salix spp"],
    CommonName:["willow"], 
    arrangement:["alternate"] 
};

Acer_circinatum={ 
    scientificName:["Acer circinatum"],
    CommonName:["vine maple"], 
    arrangement:["opposite"] 
};

Amelanchier_alnifolia={ 
    scientificName:["Amelanchier alnifolia"],
    CommonName:["serviceberry"], 
    arrangement:["alternate"] 
};

Cornus_sericea={ 
    scientificName:["Cornus sericea"],
    CommonName:["red-osier dogwood"], 
    arrangement:["opposite"] 
};

Corylus_cornuta={ 
    scientificName:["Corylus cornuta"],
    CommonName:["hazelnut"], 
    arrangement:["alternate"] 
};

Holodiscus_discolor={ 
    scientificName:["Holodiscus discolor"],
    CommonName:["ocean spray"], 
    arrangement:["alternate"] 
};

Lonicera_involucrata={ 
    scientificName:["Lonicera involucrata"],
    CommonName:["twinberry"], 
    arrangement:["opposite"] 
};

Oemleria_cerasiformis={ 
    scientificName:["Oemleria cerasiformis"],
    CommonName:["Indian plum"], 
    arrangement:["alternet"] 
};

Rosa_nutkana={ 
    scientificName:["Rosa nutkana"],
    CommonName:["Nootka rose"], 
    arrangement:["alternet"],
    thorns:["true"]
};

Rosa_gymnocarpa={ 
    scientificName:["Rosa gymnocarpa"],
    CommonName:["bald-hip rose"], 
    arrangement:["alternet"],
    thorns:["true"]
};

Philadelphus_lewisii={ 
    scientificName:["Philadelphus lewisii"],
    CommonName:["mock-orange"], 
    arrangement:["opposite"] 
};

Physocarpus_capitatus={ 
    scientificName:["Physocarpus capitatus"],
    CommonName:["Pacific ninebark"], 
    arrangement:["alternate"] 
};

Ribes_sanguineum={ 
    scientificName:["Ribes sanguineum"],
    CommonName:["red flowering currant"], 
    arrangement:["alternate"] 
};

Rubus_leucodermis={ 
    scientificName:["Rubus leucodermis"],
    CommonName:["Blackcap, whitebark raspberry"], 
    arrangement:["alternate"],
    thorns:["true"]
};

Rubus_nutkanas={ 
    scientificName:["Rubus nutkanas"],
    CommonName:["thimbleberry"], 
    arrangement:["alternate"] 
};

Rubus_spectabilis={ 
    scientificName:["Rubus spectabilis"],
    CommonName:["salmonberry"], 
    arrangement:["alternate"],
    thorns:[true]
};

Sambucus_racemosa={ 
    scientificName:["Sambucus racemosa"],
    CommonName:["red elderberry"], 
    arrangement:["opposite"] 
};

Spiraea_douglasii={ 
    scientificName:["Spiraea douglasii"],
    CommonName:["hardhack"], 
    arrangement:["alternate"] 
};

Symphoricarpos_albus={ 
    scientificName:["Symphoricarpos albus"],
    CommonName:["snowberry"], 
    arrangement:["opposite"] 
};

Vaccinium_parvifolium={ 
    scientificName:["Vaccinium parvifolium"],
    CommonName:["red huckleberry"], 
    arrangement:["alternate"] 
};

twigArray = [Acer_macrophyllum, Alnus_rubra, Cornus_nuttallii, Frangula_purshiana, Fraxinus_latifolia, Malus_fusca, Populus_tremuloides, Populus_trichocarpa, Prunus_emarginata, Quercus_garryana, Acer_circinatum, Amelanchier_alnifolia, Cornus_sericea, Corylus_cornuta, Holodiscus_discolor, Lonicera_involucrata, Oemleria_cerasiformis, Rosa_nutkana, Rosa_gymnocarpa, Philadelphus_lewisii, Physocarpus_capitatus, Ribes_sanguineum, Rubus_leucodermis, Rubus_nutkanas, Rubus_spectabilis, Sambucus_racemosa, Spiraea_douglasii, Symphoricarpos_albus, Vaccinium_parvifolium];


//card Maker ------------------------------------------------------------------

//get location-------
const body = document.getElementsByTagName("body")[0];
const cardAmount = twigArray.length;

//make cards-----------------------------------------------
for (let i=0; i < cardAmount; i++){

    
    //make card
    const twigCard = document.createElement("div"); //make div
    twigCard.setAttribute("class", "card");          //add class
    twigCard.setAttribute("Id", "card-" + twigArray[i].scientificName);    //label each box
    document.getElementById("cardHolder").appendChild(twigCard); //adds it to html

    //add common name 
    const cardCommonName = document.createElement("h1"); //make h1
    cardCommonName.setAttribute("class", "CommonName");          //add class
    cardCommonName.setAttribute("Id", "LabelCommon-" + twigArray[i].CommonName);    //label
    document.getElementById("card-" + twigArray[i].scientificName).appendChild(cardCommonName); //adds it to html
    document.getElementById("LabelCommon-" + twigArray[i].CommonName).innerHTML = twigArray[i].CommonName; //set to value

    //add scientific name 
    const cardscientificName = document.createElement("h2"); //make h2
    cardscientificName.setAttribute("class", "ScientificName");          //add class
    cardscientificName.setAttribute("Id", "LabelScientific-" + twigArray[i].scientificName);    //label
    document.getElementById("card-" + twigArray[i].scientificName).appendChild(cardscientificName); //adds it to html
    document.getElementById("LabelScientific-" + twigArray[i].scientificName).innerHTML = twigArray[i].scientificName; //set to value

}