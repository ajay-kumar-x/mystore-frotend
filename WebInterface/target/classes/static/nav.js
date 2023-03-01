
// Accordion
function myAccFunc(thisA,subcategory) {
  // to show the subcategory
  var x = document.getElementById(subcategory);
  if (x.className.indexOf("w3-show") == -1) {
    x.className += " w3-show";
  } else {
    x.className = x.className.replace(" w3-show", "");
  }
  //to change the caret direction.
  let caret = thisA.querySelector(".fa");

   if(caret.className.includes('right')){
      caret.className ='fa fa-caret-down';
        }
      else{caret.className='fa fa-caret-right'}
}




// Open and close sidebar
function w3_open() {
  document.getElementById("mySidebar").style.display = "block";
  document.getElementById("myOverlay").style.display = "block";
}

function w3_close() {
  document.getElementById("mySidebar").style.display = "none";
  document.getElementById("myOverlay").style.display = "none";
}
