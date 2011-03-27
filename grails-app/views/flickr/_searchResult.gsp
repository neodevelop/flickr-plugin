<div id="album${id}">
  <div id="photos${id}">
  <g:each in="${urls}" var="url" status="i">
    <a href="${url.replace('_s.jpg','_b.jpg')}">
      <img src='${url}' title="${tag}${i}" border="0"/>
    </a>
  </g:each>
  </div>
</div>

<div id="info${id}">
  ${perPage} from ${page} of ${pages}
</div>

<g:if test="${animated}">
<script type="text/javascript">

  $("head").append("<link>");
  css = $("head").children(":last");
  css.attr({
    rel:  "stylesheet",
    type: "text/css",
    href: "http://web-argument.com/wp-content/uploads/2011/03/accordionImageMenu.css"
  });

  $.getScript("http://web-argument.com/wp-content/uploads/2011/03/accordionImageMenu-0.4.min_.js",function(){
    $('#photos${id}').AccordionImageMenu({
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
</g:if>

