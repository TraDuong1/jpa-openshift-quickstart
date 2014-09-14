JPA in OpenShift Quickstart
===============================

This is a simple Red Hat OpenShift example containing a Tomcat that 
connects to noSQL DB (Mongo2.4) and relationalDB (MySql 5.5)
and perform persistence via JPA persistence APIs that is managed by Kundera lib.


Running on OpenShift
----------------------------

Create an account at https://www.openshift.com

Create Tomcat application with Mongo2.4 DB and MySql 5.5 DB

    rhc app create myapp jbossews-2.0 mysql-5.5 mongodb-2.4

Add this upstream repo

    cd myapp
    git remote add upstream https://github.com/TraDuong1/jpa-openshift-quickstart
    git pull -s recursive -X theirs upstream master

Modify the persistence.xml with the correct DB credentials for each DB from
    rhc show-app myapp

Then push the repo upstream

    git push

That's it, you can now checkout your application at:

    http://myapp-$yournamespace.$youropenshiftserver/persist



