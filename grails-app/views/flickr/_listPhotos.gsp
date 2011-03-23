<div id="photos">
<g:each in="${urls}" var="url" status="i">
  <a href="${url.replace('_s.jpg','_b.jpg')}"><span>${tag}</span><img src='${url}' /></a>
</g:each>
</div>

<!-- TODO: Make pagination
${page} of ${pages}
<g:remoteLink controller="flickr" action="changePage" update="album" params="[tag:tag,perPage:perPage,page:page]">
<b> > </b>
</g:remoteLink>
-->


<script type="text/javascript">
    $('#photos').AccordionImageMenu({
      'color': '#000000',
      'duration': 350,
      'openDim': 90,
      'closeDim': 25,
      'effect': 'easeOutBack',
      'width':300,
      'height':80
    });
</script>