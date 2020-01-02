var headerBanner = document.querySelector('header');
var headerScript = document.getElementById('navId');
var mainDiv = document.getElementById('mainDivId');
var viewPort = window.matchMedia("(max-width: 849px)");
var childButtonAppended = false;
var myNavBarButton = document.createElement('a');
myNavBarButton.className = 'openLinks';
myNavBarButton.innerHTML = "Navigering";

function addNavigationButton(value) {
  if (value.matches) {
    childButtonAppended = true;
    headerScript.appendChild(myNavBarButton);
  } else {
    if (childButtonAppended === true) {
      headerScript.removeChild(myNavBarButton);
      headerScript.className = "navDiv";
    }
  }
}

function openNav(event) {
  if (headerScript.className === "navDiv") {
    headerScript.className += "Response";
    mainDiv.className += "Response";
  } else {
    headerScript.className = "navDiv";
    mainDiv.className = "mainDiv";
  }
}

function setHeader() {
  var random = Math.floor(Math.random() * 5) + 1;
  headerBanner.className = 'banner' + random;
}

function main() {
  console.log("Header Script loaded!");
  setHeader();
  addNavigationButton(viewPort);
  viewPort.addListener(addNavigationButton);
  myNavBarButton.addEventListener('click', openNav)
}

main();