#!/bin/bash

mvn package
docker-compose up -d --build
