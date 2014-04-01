elastic-template
==============
A simple template to configure Elastic Search with MongoDB and Spring Data

Install Elastic
==============
1. wget https://download.elasticsearch.org/elasticsearch/elasticsearch/elasticsearch-1.1.0.deb

sudo dpkg -i elasticsearch-1.1.0.deb

sudo service elasticsearch start

#curl http://localhost:9200

2. $ES_HOME/bin/plugin -install elasticsearch/elasticsearch-mapper-attachments/1.6.0

3. $ES_HOME/bin/plugin --install com.github.richardwilly98.elasticsearch/elasticsearch-river-mongodb/2.0.0

Usually ES_HOME="/usr/share/elasticsearch" (use @whereis elastic)


Create fitst index
==============

curl -XPUT "localhost:9200/_river/mongodb/_meta" -d '
{
"type": "mongodb",
"mongodb": {
"servers": [
{ "host": "127.0.0.1", "port": 27017 }
],
"options": { "secondary_read_preference": true },
"db": "test",
"collection": "template_some_bean"
},
"index": {
"name": "somebean",
"type": "template_some_bean"
}
}'


verify:

curl -XGET 'http://localhost:9200/somebean/_search?q=title:SOME_TITLE'



Jenkins Build
==============
http://azee.people.yandex.net/job/elastic-template/
