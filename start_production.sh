#!/bin/bash
branch=$1
git fetch --all
git reset --hard HEAD
git checkout $branch || git pull origin $branch
docker-compose build
docker-compose up -d
