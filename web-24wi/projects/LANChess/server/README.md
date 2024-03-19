# api examples

## login/register
#### login
`
curl -X POST -H "Content-Type: application/json" ${domain}/login -d '{ "username":"example_name", "password": "example_pass"}'
`
#### register
`
curl -X POST -H "Content-Type: application/json" ${domain}/register -d '{ "username":"example_name", "password": "example_pass"}'
`

## users
#### GET /api/users
`
curl -H "Authorization: Bearer $JWT" ${domain}/api/users
`

#### GET /api/user/:id
`
curl -H "Authorization: Bearer $JWT" ${domain}/api/users/${ID}
`

#### PUT /api/users/:id
`
curl -X PUT -H "Authorization: Bearer $JWT" -H "Content-Type: application/json"  ${domain}/api/users/$ID -d '{"username": "new_username}'
`

#### DELETE /api/users/:d 
`
curl -X DELETE -H "Authorization: Bearer $JWT" ${domain}/api/users/${ID}
`

## games
#### GET /games/:id
`
curl -H "Authorization: Bearer $JWT" ${domain}/api/games/${GAMEID}
`
#### GET /games
`
curl -H "Authorization: Bearer $JWT" ${domain}/api/games
`
#### POST /games
`
curl -X POST -H "Authorization: Bearer $JWT" ${domain}/api/games
`
#### PUT /games/:id
`
curl -X PUT -H "Content-Type: application/json" -H "Authorization: Bearer $JWT" ${domain}/api/games/${GAMEID} -d '{"status":"COMPLETE"}'
`
#### DELETE /games/:id
`
curl -X DELETE -H "Authorization: Bearer $JWT" ${domain}/api/games/${GAMEID}
`

## moves
#### GET /moves
`
curl -X GET -H "Authorization: Bearer $JWT" -H "Content-Type: application/json" localhost:5000/api/moves -d '{"gameId": "'$GAMEID'"}'
`
#### POST /moves
`
curl -X POST -H "Authorization: Bearer $JWT" -H "Content-Type: application/json" localhost:5000/api/moves -d '{"fenString":"test","gameId": "'$GAMEID'","userId":"'$USERID'"}'
`


## game_participants
#### POST /gameParticipant
`curl -X POST -H "Authorization: Bearer $JWT" -H "Content-Type: application/json" localhost:5000/api/gameParticipant -d '{"gameId": "'$GAMEID'", "userId":"'$USERID'"}'`
