# cli-extender
Module that extends jboss-cli. Ext new command - <b>download</b> <br/>
The command downloads a file from 'url' to WildFly local directory 

## download
paramms
- --url=http://xxxx  
- --dst=/tmp

## how to install
- copy the artifact to WildFly local directory  
- In jboss-cli install artifact  as a Module: <i> module add --name=cli-extender --resources=~/cli-extender-0.0.1.jar --dependencies=org.jboss.as.controller </i>
- In jboss-cli register the Module as a Extension:  <i> /extension=cli-extender:add </i>
