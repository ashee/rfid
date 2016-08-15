#!/bin/sh
# mvnify-tm.sh

TM_VERSION=1.27.3.16
TM_HOME=$PWD/thingmagic

mvn deploy:deploy-file \
	-Durl=file:./repo/ \
	-Dfile=$TM_HOME/mercuryapi.jar \
	-DgroupId=com.thingmagic \
	-DartifactId=mercuryapi \
	-Dpackaging=jar \
	-Dversion=${TM_VERSION}

mvn deploy:deploy-file \
	-Durl=file:./repo/ \
	-Dfile=$TM_HOME/ltkjava-1.0.0.6.jar \
	-DgroupId=org.llrp.ltk \
	-DartifactId=ltkjava \
	-Dpackaging=jar \
	-Dversion=1.0.0.6

mvn deploy:deploy-file \
	-Durl=file:./repo/ \
	-Dfile=$TM_HOME/ltkthingmagic.jar \
	-DgroupId=org.llrp.ltk \
	-DartifactId=ltkthingmagic \
	-Dpackaging=jar \
	-Dversion=1.0.0.6



