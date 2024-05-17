#!/bin/sh


wget https://simontechnology.org/ourpages/auto/2013/1/23/53406450/Catcher%20in%20the%20Rye%20Text.pdf
mv 'Catcher in the Rye Text.pdf' catcher.pdf
pdftotext catcher.pdf

