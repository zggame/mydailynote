#!/bin/bash
cd ~/mydailynote
svn update
svn add *.tex
echo "office">today.txt
date>>today.txt
svn commit -F today.txt

