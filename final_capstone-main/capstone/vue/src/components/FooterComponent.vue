<template>
  <footer>
    <div class="footer" role="img" aria-label="Cream White Background with Black and Gray Drawings of Flowers">
      <div class="row">
        <a href="#"><i class="fa fa-facebook" animated></i></a>
        <a href="#"><i class="fa fa-instagram" animated></i></a>
        <a href="#"><i class="fa fa-youtube" animated></i></a>
        <a href="#"><i class="fa fa-twitter" animated></i></a>
      </div>

      <div class="row">
        <ul>
          <li><a href="#">Contact Us</a></li>
          <li><a href="http://localhost:8080/about">About The Developers</a></li>
          <li><a href="#">Our Services</a></li>
          <li><a href="#">Privacy Policy</a></li>
          <li><a href="#">Terms & Conditions</a></li>
          <li><a href="#">Career</a></li>
        </ul>
      </div>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  background-image: linear-gradient(
    rgba(300, 300, 300, 0.8),
    rgba(300, 300, 300, 0.5)
  ),
  url(../img/footer_background.png);
  padding: 1px 0;
  font-family: 'Play', sans-serif;
  text-align: center;
  box-sizing: border-box;
}

.footer .row {
  width: 100%;
  padding: 0.6% 0;
  color: #4a7c59;
  font-size: 0.8em;
}

.footer .row a {
  text-decoration: none;
  color: #4a7c59;
  transition: 0.5s;
}

.footer .row a:hover {
  color:#7c594a;
}

.footer .row ul li {
  display: inline-block;
  margin: 0 30px;
}

.footer .row a i {
  font-size: 2em;
  margin: 0 1%;
}
</style>