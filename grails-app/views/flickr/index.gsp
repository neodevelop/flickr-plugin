<html>
<head>
  <title>Test Plugin</title>
  <g:javascript library="jquery" plugin="jquery" />
  <jqui:resources/>
</head>
<body>
  <h1>Congrats!!! Flickr TagLib it's installed...</h1>
  
  <flickr:search tags='grailsmx' perPage="12"/>
  
  <flickr:search tags='grailsmx' page="3" perPage="8"/>
  
  <flickr:search tags='grailsmx,springhispano' animated='true' perPage="30"/>

</body>
</html>