<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Top Store - Best Cosmetics</title>
</head>
<style>
    .btn-dark {
        font-family: 'Montserrat', sans-serif;
        text-transform: uppercase;
        width: 200px;
    }

    .card-title {
        font-size: xx-large;
    }

    .card-text {
        font-size: x-large;
    }
</style>
<%@include file="header.jsp" %>
<body>
<a class="btn btn-dark" style="position: absolute; right: 40px; top: 40px; padding: 1%; font-size: x-large"
   href="https://time.is">${time}</a>
<br>
<div style="padding-left: 10%; padding-right: 10%; padding-bottom: 5%;text-align: center">
    <div class="card-deck">
        <div class="card">
            <img src="https://i.imgur.com/3xzTYP2.jpg?1" class="card-img-top" alt="dior">
            <div class="card-body">
                <h5 class="card-title">DIOR BACKSTAGE FOUNDATION</h5>
                <br>
                <p class="card-text"><em>The Dior Backstage Face & Body Foundation is the Dior makeup artists'
                    secret weapon
                    to create an instantly flawless complexion with ultra-buildable coverage,
                    from a natural nude glow to high-coverage perfection. Works for all types of skin,
                    is long-lasting even on oily skin.</em></p>
                <br>
                <a class="btn btn-dark" href="${pageContext.request.contextPath}/products/all"
                   style="padding: 3%">2000.00 UAH</a>
            </div>
        </div>
        <div class="card">
            <img src="https://i.imgur.com/aFiFGCq.jpg?1" class="card-img-top" alt="exposed">
            <div class="card-body">
                <h5 class="card-title">LIME CRIME PRELUDE EXPOSED</h5>
                <br>
                <p class="card-text"><em>Roll into seashell shades of color with Prelude Exposed,
                    an ode to the birth of our goddess, Venus! Explore alluring looks using 8 warm
                    and cool-tone pigmented shadows in matte, sparkle matte, metallic,
                    and sheer iridescent finishes that are as charming and refined as a seashore.</em></p>
                <br>
                <a class="btn btn-dark" href="${pageContext.request.contextPath}/products/all"
                   style="padding: 3%">700.00 UAH</a>
            </div>
        </div>
        <div class="card">
            <img src="https://i.imgur.com/ekH9Euu.png?1" class="card-img-top" alt="guerlain">
            <div class="card-body">
                <h5 class="card-title">GUERLAIN CILS D’ENFER SO VOLUME</h5>
                <br>
                <p class="card-text"><em>From the first sweep of the brush, the gentle, creamy and elastic formula
                    creates significantly thicker lashes. Infused with a conditioning action to protect the lashes,
                    it is enriched with adhesive oil to prevent running or smudging and ultra-glossy oil
                    for an intense black.</em></p>
                <br>
                <a class="btn btn-dark" href="${pageContext.request.contextPath}/products/all"
                   style="padding: 3%">1200.00 UAH</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
