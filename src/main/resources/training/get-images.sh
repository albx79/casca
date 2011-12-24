#!/bin/sh

BASE=http://media.wizards.com/images/magic/tcg/products/nph/

for IMG in $(cat image-list.txt); do wget $BASE$IMG; done
