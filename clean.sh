#!/bin/bash

find sth -name *.class -exec rm "{}" +
find sth -name *.html -exec rm "{}" +
find . -name func -exec rm "{}" \;
find . -name func2 -exec rm "{}" \;
find . -name proj3 -exec rm "{}" \;