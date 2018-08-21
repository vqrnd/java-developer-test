#!/usr/bin/env bash

mongo_cmd="db.users.insert(["
test_users_count=10

while [ $test_users_count -gt 0 ]
do
    mongo_cmd=$mongo_cmd'{email: "user'$test_users_count'@test.local",password: hex_md5("testPass'$test_users_count'")}'
    if [[ $test_users_count > 1 ]]; then
    	mongo_cmd=$mongo_cmd', '
    fi
    test_users_count=`expr $test_users_count - 1`
done

mongo_cmd=$mongo_cmd']);'

mongo 127.0.0.1/auth --eval "$mongo_cmd"