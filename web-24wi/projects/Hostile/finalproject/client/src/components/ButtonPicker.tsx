import React, { useState, useEffect } from 'react';

const ButtonPicker = () => {
  
  const buttons = [
    "graphics/buttons/noedit.gif",
"graphics/buttons/truth.gif",
"graphics/buttons/dilbert.gif",
"graphics/buttons/wwwbutton.gif",
"graphics/buttons/W3Chtml.gif",
"graphics/buttons/computer.gif",
"graphics/buttons/pirvacynow.gif",
"graphics/buttons/hand_coded.gif",
"graphics/buttons/ezgif.gif",
"graphics/buttons/ieexplode.gif",
"graphics/buttons/homicidenow.gif",
"graphics/buttons/heavensgate.gif",
"graphics/buttons/ravenow.gif",
"graphics/buttons/go2hell.gif",
"graphics/buttons/html.gif",
"graphics/buttons/minesweeper.gif",
"graphics/buttons/bantime.gif",
"graphics/buttons/mailput.gif",
"graphics/buttons/nocodeandrun.gif",
"graphics/buttons/freespeechforever.gif",
"graphics/buttons/bookmark_this_page.gif",
"graphics/buttons/homernow.gif",
"graphics/buttons/alienow.gif",
"graphics/buttons/psbutton.gif",
"graphics/buttons/chill_pill.gif",
"graphics/buttons/nneighborhoodsmall.gif",
"graphics/buttons/drmario.gif",
"graphics/buttons/wheel.gif",
"graphics/buttons/freekevin.gif",
"graphics/buttons/piracy.gif",
"graphics/buttons/schoolsucks.gif",
"graphics/buttons/albutton.gif",
"graphics/buttons/freeware.gif",
"graphics/buttons/seedyourtorrents.gif",
"graphics/buttons/steam.gif",
"graphics/buttons/benis.gif",
"graphics/buttons/phonechump.gif",
"graphics/buttons/staylocal.gif",
"graphics/buttons/dootdoot.gif",
"graphics/buttons/campaignframes.gif",
"graphics/buttons/smileybutton001.gif",
"graphics/buttons/shareware.gif",
"graphics/buttons/anti-button.gif",
"graphics/buttons/digitalme.gif",
"graphics/buttons/fuckingwebmaster.gif",
"graphics/buttons/nowebp.gif",
"graphics/buttons/pepsi.gif",
"graphics/buttons/wii.gif",
"graphics/buttons/mtdew.gif",
"graphics/buttons/StardewValleyButton.gif",
"graphics/buttons/hl.gif",
"graphics/buttons/coke.gif",
"graphics/buttons/internetprivacy.gif",
"graphics/buttons/beaniebutton.gif",
"graphics/buttons/bettercallsaul.gif",
"graphics/buttons/css2.gif",
"graphics/buttons/parental.gif",
"graphics/buttons/theonion.gif",
"graphics/buttons/imissxp.gif",
"graphics/buttons/microshaft.gif",
"graphics/buttons/myspace.gif",
"graphics/buttons/Ebay.gif",
"graphics/buttons/aimlink.gif",
"graphics/buttons/geo88x31ee.gif",
"graphics/buttons/gethtmlnow.gif",
"graphics/buttons/analien.gif",
"graphics/buttons/hatemac.gif",
"graphics/buttons/graphicdesign",
"graphics/buttons/killerwebsitesfish.gif"

  ];

  
  const [selectedButtons, setSelectedButtons] = useState([]);

  // pick two random buttons
  const pickRandomButtons = () => {
    const randomButtons = [];
    while (randomButtons.length < 2) {
      const randomIndex = Math.floor(Math.random() * buttons.length);
      const randomButton = buttons[randomIndex];
      if (!randomButtons.includes(randomButton)) {
        randomButtons.push(randomButton);
      }
    }
    setSelectedButtons(randomButtons);
  };

  useEffect(() => {
    pickRandomButtons();
  }, []); 

  return (
    <div className='flex flex-row'>
      {selectedButtons.map((button, index) => (
        <img  key={index} src={"https://webpage-1990-colourised.neocities.org/" + button} alt={`Button ${index + 1}`} className="mr-4 mb-1" />
      ))}
    </div>
  );
};

export default ButtonPicker;
