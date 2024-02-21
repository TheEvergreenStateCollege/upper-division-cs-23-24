# Welcome to the pswish-corp-Frankenbits dashboard. Adopted from the React dashboard tutorial from Ed Roh. 

To Access this dashboard, navigate to https://www.pswish-corp.arcology.builders

## To run this locally. 
* Clone my prod/ directory.
* Run `npm i` or `pnpm i` to download the node modules
* If you run into problems installing, try downloading and running Yarn. I found I only needed to run Yarn once and then I used `pnpm` without any problems.

* Now change into the and run `python3 driver_flask/app.py` 
* If you already have a service on port 5000, you can run `flask run -p 5001` or `gunicorn -b :5001 app:app`