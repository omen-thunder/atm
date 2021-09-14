#!/bin/bash
branch=$1
git fetch --all
git reset --hard HEAD
git checkout $branch || echo "Could not checkout to $branch"
git pull origin $branch || echo "Cloud not pull from origin/$branch"
docker-compose build && docker-compose up -d
