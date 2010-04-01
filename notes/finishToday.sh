#!/bin/bash
cd ~/mydailynote
svn update
svn add *.tex
echo "cq60">today.txt
date>>today.txt
svn commit -F today.txt

