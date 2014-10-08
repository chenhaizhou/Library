#!/usr/bin/env bash

authorName=`git config --get user.name`

if [ -z "$authorName" ] 
then
    read -p "Please input your name:" authorName 
fi

read -p "Please input your pair name:" pairName 
read -p "Please input story name:" storyName 
read -p "Please input commit :" commit

#echo "commit:${commit}" 
#echo "storyname:${storyName}" 

commitName="${authorName} & ${pairName}"

#echo "commitname:${commitName}" 

git commit -m "#${storyName}: [${commitName}] - ${commit}"
