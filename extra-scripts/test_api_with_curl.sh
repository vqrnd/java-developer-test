#!/bin/sh

curl -v --header "Content-Type: application/json" --request POST --data '{"email":"user4@test.local","password":"testPass4"}' http://127.0.0.1:7000/api/v1/auth