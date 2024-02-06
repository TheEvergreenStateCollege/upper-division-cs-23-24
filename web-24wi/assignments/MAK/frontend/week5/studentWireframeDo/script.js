function hideAll(){
    document.getElementById("login-page").style.display = "none";
    document.getElementById("home-page").style.display = "none";
    document.getElementById("search-page").style.display = "none";
  }
  
  function home() {
    hideAll();
    document.getElementById("home-page").style.display = "block";
  }
  
  function search() {
    hideAll();
    document.getElementById("search-page").style.display = "block";
  }