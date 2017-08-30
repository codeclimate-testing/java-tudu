Install GWT into the "gwt-installation" directory.

In order to add gwt-user.jar to your local Maven Repository, go to the
"gwt-installation" directory and type :

mvn install:install-file -DgroupId=com.google.gwt -DartifactId=gwt-servlet -Dversion=1.4.59 -Dpackaging=jar -Dfile=gwt-servlet.jar
