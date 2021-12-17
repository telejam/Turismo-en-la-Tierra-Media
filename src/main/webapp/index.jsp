<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="partials/head.jsp"></jsp:include>



<link https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js>
<script type="text/javascript" src="scripts/jquery.js"></script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Lobster+Two:ital@1&display=swap');
</style>



<style>


* {
    margin: 0;
    padding: 0;
    outline: none;
    border: none;
    box-sizing: border-box
}

*:before,
*:after {
    box-sizing: border-box
}

html,
body {
    min-height: 100%
}

body {
    background-image: url('https://thumbs.dreamstime.com/b/sch%C3%B6nes-foto-56976007.jpg');
   background-repeat: no-repeat;
   background-attachment: fixed;
   background-size: cover;
   background-position: center;

   color: #FFFFFF;
}

h1 {
    display: table;
    margin: 5% auto 0;
    text-transform: uppercase;
    font-family: 'Lobster Two', cursive;
    font-size: 3em;
    font-weight: 400;
    color: #B8860B;
    text-shadow: 0 1px white, 0 1px black
}

.container1 {
    margin: 4% auto;
    width: 210px;
    height: 140px;
    position: relative;
    perspective: 1000px
}

#carousel {
    width: 100%;
    height: 100%;
    position: absolute;
    transform-style: preserve-3d;
    animation: rotation 20s infinite linear
}

#carousel:hover {
    animation-play-state: paused
}

#carousel figure {
    display: block;
    position: absolute;
    width: 90%;
    height: 50%px;
    left: 10px;
    top: 10px;
    background: black;
    overflow: hidden;
    border: solid 3px black
}

#carousel figure:nth-child(1) {
    transform: rotateY(0deg) translateZ(288px)
}

#carousel figure:nth-child(2) {
    transform: rotateY(40deg) translateZ(288px)
}

#carousel figure:nth-child(3) {
    transform: rotateY(80deg) translateZ(288px)
}

#carousel figure:nth-child(4) {
    transform: rotateY(120deg) translateZ(288px)
}

#carousel figure:nth-child(5) {
    transform: rotateY(160deg) translateZ(288px)
}

#carousel figure:nth-child(6) {
    transform: rotateY(200deg) translateZ(288px)
}

#carousel figure:nth-child(7) {
    transform: rotateY(240deg) translateZ(288px)
}

#carousel figure:nth-child(8) {
    transform: rotateY(280deg) translateZ(288px)
}

#carousel figure:nth-child(9) {
    transform: rotateY(320deg) translateZ(288px)
}

#carousel figure img {
    height: 150px
}

img:hover {
    -webkit-filter: grayscale(0);
    transform: scale(1.2, 1.2)
}

@keyframes rotation {
    from {
        transform: rotateY(0deg)
    }

    to {
        transform: rotateY(360deg)
    }
}





</style>




</head>
<body>

	<jsp:include page="partials/nav.jsp"></jsp:include>

	<main class="container">
	<!-- 	<div class="bg-light p-4 rounded">  -->
		
				<div>
		
			<h1>
				¡Bienvenido, <c:out value="${user.username}" />!
			</h1>
		</div>
	</main>
	
	<div class="container1">
    <div id="carousel">
        <figure><img src="https://www.musicaenaccion.com/wp-content/uploads/2019/02/tierra_media_w.jpg"></figure>
        <figure><img src="https://es.bcdn.biz/Images/2014/3/23/658114d5-d3da-4dd5-a609-bbd810e9281b.jpg"></figure>
        <figure><img src="https://1.bp.blogspot.com/-PQwVkyvLYIU/UT-abELSszI/AAAAAAAAAbk/c3GvL711UX8/s320/9c3ca603.jpg"></figure>
        <figure><img src="https://wallpapers-fenix.eu/lar/210209/095211213.jpg"></figure>
        <figure><img src="https://i.imgur.com/2W8y7Fe.jpeg"></figure>
        <figure><img src="https://underworlds.games/wp-content/uploads/2019/09/la-comarca.jpg">
        </figure>
        <figure><img src="https://www.linux.com/wp-content/uploads/2019/08/banner.jpg"></figure>
        <figure><img src="https://t1.kn3.net/taringa/0/D/2/C/B/B/Frantincho92/DCE.jpg" ></figure>
        <figure><img src="https://2.bp.blogspot.com/-OfVYh8uIVG4/UpR_GFFBI1I/AAAAAAAAAa8/cJFuk_zaLi0/s400/El+se%C3%B1or+de+los+anillos+matamata.jpg" ></figure>


    </div>
</div>

	
	
	
	
	
</body>
</html>
