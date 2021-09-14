#!/bin/bash
branch=$1
git fetch --all
git reset --hard origin/"$branch"
docker-compose up -d
