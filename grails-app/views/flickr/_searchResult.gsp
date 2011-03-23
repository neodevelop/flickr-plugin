<div id="album">
  <g:render template="/flickr/listPhotos" plugin="flickr" model="[urls:urls,tag:tag,page:page,pages:pages,perPage:perPage]" />
</div>

<script type="text/javascript">

  $("head").append("<link>");
  css = $("head").children(":last");
  css.attr({
    rel:  "stylesheet",
    type: "text/css",
    href: "http://web-argument.com/wp-content/uploads/2011/03/accordionImageMenu.css"
  });

  $.getScript("http://web-argument.com/wp-content/uploads/2011/03/accordionImageMenu-0.4.min_.js",function(){
    $('#photos').AccordionImageMenu({
      'color': '#000000',
      'duration': 350,
      'openDim': 90,
      'closeDim': 25,
      'effect': 'easeOutBack',
      'width':300,
      'height':80
    });
  });
</script>