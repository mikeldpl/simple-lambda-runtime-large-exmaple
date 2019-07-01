#!/usr/bin/env bash

aws lambda update-function-code --function-name native-demo-lambda-large --zip-file fileb://target/lambda-native-large.zip