#!/bin/bash
cd ~/svn/notes
svn update
svn add *.tex
echo "office">today.txt
date>>today.txt
svn commit -F today.txt

