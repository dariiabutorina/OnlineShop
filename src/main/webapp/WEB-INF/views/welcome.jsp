<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Top Store - Best Cosmetics</title>
</head>
<style>
    .btn-light {
        font-family: 'Montserrat', sans-serif;
        text-transform: uppercase;
    }
</style>
<%@include file="header.jsp" %>
<body>
<div style="text-align: center">
    <a class="btn btn-light" style="position: absolute; right: 10px; top: 10px"
       href="https://time.is">${time}</a>
    <br>
    <div class="card-deck">
        <div class="card">
            <img src="https://i.imgur.com/3xzTYP2.jpg?1" class="card-img-top" alt="dior">
            <div class="card-body">
                <h5 class="card-title">DIOR BACKSTAGE FOUNDATION</h5>
                <p class="card-text">The Dior Backstage Face & Body Foundation is the Dior makeup artists' secret weapon
                    to create an instantly flawless complexion with ultra-buildable coverage,
                    from a natural nude glow to high-coverage perfection.</p>
                <p class="card-text"><small class="text-muted">2000.00 HRN</small></p>
            </div>
        </div>
        <div class="card">
            <img src="https://i.imgur.com/aFiFGCq.jpg?1" class="card-img-top" alt="exposed">
            <div class="card-body">
                <h5 class="card-title">LIME CRIME PRELUDE EXPOSED</h5>
                <p class="card-text">Roll into seashell shades of color with Prelude Exposed,
                    an ode to the birth of our goddess, Venus! Explore alluring looks using 8 warm
                    and cool-tone pigmented shadows in matte, sparkle matte, metallic,
                    and sheer iridescent finishes that are as charming and refined as a seashore.</p>
                <p class="card-text"><small class="text-muted">700.00 HRN</small></p>
            </div>
        </div>
        <div class="card">
            <img src="https://i.imgur.com/ekH9Euu.png?1" class="card-img-top" alt="guerlain">
            <div class="card-body">
                <h5 class="card-title">GUERLAIN CILS D’ENFER SO VOLUME</h5>
                <p class="card-text">From the first sweep of the brush, the gentle, creamy and elastic formula
                    creates significantly thicker lashes. Infused with a conditioning action to protect the lashes,
                    it is enriched with adhesive oil to prevent running or smudging and ultra-glossy oil
                    for an intense black.</p>
                <p class="card-text"><small class="text-muted">1200.00 HRN</small></p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
