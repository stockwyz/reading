FROM 172.16.20.101:5000/centos:7.6.1810
MAINTAINER liubo1@reignwood.com

RUN yum install -y http://it-repo.reignwood.com/cobbler/repo_mirror/Centos7-Sdk/jdk.rpm && \
    mkdir /data
COPY evaluation.jar /data
ENTRYPOINT ["java","-jar", "-Duser.timezone=GMT+08","/data/evaluation.jar"]
