var memberContainer = document.getElementById("member_container");
var memberContainerHtml = "";
var switchButton = document.getElementById("switchButton");
var artistButton = document.getElementById("artistButton");
var genreButton = document.getElementById("genreButton");
var albumButton = document.getElementById("albumButton");
var trackButton = document.getElementById("trackButton");
var sortAtoZButton = document.getElementById("sortAtoZ");
var sortZtoAButton = document.getElementById("sortZtoA");
var sortByParentButton = document.getElementById("sortByParent");
var sortByGenreButton = document.getElementById("sortByGenre");
var filterInput = document.getElementById("filterInput");

// Sets the XMLHTTP Request appropriately
function getRequest() {
  var xhr;
  if (window.XMLHttpRequest) {
    xhr = new XMLHttpRequest();
  } else if (window.ActiveXObject) {
    xhr = new ActiveXObject("Microsoft.XMLHTTP");
  }
  return xhr;
}

function loadData(dataType) {
  var ajax = getRequest();
  var urlString = "";
  if (dataType === "artist"){
    urlString = "http://localhost:8080/api/artist/";
  } else if (dataType === "album") {
    urlString = "http://localhost:8080/api/album/";
  } else if (dataType === "genre") {
    urlString = "http://localhost:8080/api/genre/";
  } else if (dataType === "track") {
    urlString = "http://localhost:8080/api/track/";
  }


  ajax.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      var data = JSON.parse(this.responseText);
      memberContainerHtml = "";
      if (dataType === "artist") {
        insertArtistMember(data);
      } else if (dataType === "album") {
        insertAlbumMember(data);
      } else if (dataType === "genre") {
        insertGenreMember(data);
      } else if (dataType === "track") {
        insertTrackMember(data);
      }
    }
  };
  ajax.open("GET", urlString, true);
  ajax.send();
}

function insertArtistMember(data) {
  for (var i = 0; i < data.length; i++) {
    memberContainerHtml += "<div class='divImageMember'><img alt='" + data[i].name + "' height='370' width='370' src='" + data[i].image + "'/><h3 class ='memberTitleHeadder'>" + data[i].name + "</h3><p>" + data[i].description + "</p></div>";
  }
  // html += "";
  memberContainer.innerHTML = memberContainerHtml;
  console.log(memberContainer);
}

function insertAlbumMember(data) {
  for (var i = 0; i < data.length; i++) {
    memberContainerHtml += "<div class='divImageMember'><img alt='" + data[i].name + "' height='370' width='370' src='" + data[i].image + "'/><h3 class ='memberTitleHeadder'>" + data[i].name + "</h3><p>" + data[i].description + "</p></div>";
  }
  // html += "";
  memberContainer.innerHTML = memberContainerHtml;
}
function insertGenreMember(data) {
  for (var i = 0; i < data.length; i++) {
    memberContainerHtml += "<div class='divImageMember'><h3 class ='memberTitleHeadder'>" + data[i].name + "</h3><p>" + data[i].description + "</p></div>";
  }
  memberContainer.innerHTML = memberContainerHtml;
}

function insertTrackMember(data) {
  for (var i = 0; i < data.length; i++) {
    memberContainerHtml += "<div class='divImageMember'><h3 class ='memberTitleHeadder'>" + data[i].name + "</h3><p>" + data[i].description + "</p></div>";
  }
  memberContainer.innerHTML = memberContainerHtml;
}

function insertMembersWithNoImage() {
  var html = "";
  for (var i = 0; i < data.length; i++) {
    html += insertMemberWithoutImage(data[i]);
  }
}
// function insertMember(member) {
//   var html = "";
//   // var artistContainer = document.createElement('div');
//   // artistContainer.className = "divImageMember";
//   var name = member.name;
//   var description = member.description;
//   // var genre = member.genre[0];
//   var imgLocation = member.image;
//   // html += "<div class='divImageMember'><img alt=\"lol\" height=\"370\" width=\"370\" src=\"" + imgLocation + "\"/><p>"+ name +"</p><p>"+ genre +"</p>\"<p>"+ description +"</p></div>";
//   // html += "<div class='divImageMember'><img alt=\"+ member.name +\" height=\"370\" width=\"370\" src=\"" + imgLocation + "\"/><p>"+ name +"</p><p>"+ description +"</p></div>";
//   html +=
//   return html;
// }

function insertMemberWithoutImage(member) {
  var html = "";
  // var artistContainer = document.createElement('div');
  // artistContainer.className = "divImageMember";
  var name = member.name;
  var description = member.description;
  // var genre = member.genre[0];
  var imgLocation = member.image;
  html += "<div class='divImageMember'><p>" + member.name + "</p><p>" + member.description + "</p></div>";
  return html;
}

function setButtons() {
  switchButton.addEventListener("click", function() {
    console.log("hey");
    if (memberContainer.className === "member_grid") {
      memberContainer.className = "member_list";
      return false;
    } else if(memberContainer.className === "member_list") {
      memberContainer.className = "member_grid";
    }
  });
  artistButton.addEventListener("click", function() {
    loadData("artist");
  });
  albumButton.addEventListener("click", function() {
    loadData("album");
  });
  genreButton.addEventListener("click", function() {
    loadData("genre");
  });
  trackButton.addEventListener("click", function() {
    loadData("track");
  });
  sortAtoZButton.addEventListener("click", function() {
    sort("AtoZ");
  });
  sortZtoAButton.addEventListener("click", function() {
    sort("ZtoA");
  });
  sortByParentButton.addEventListener("click", function() {
    sort("byParent");
  });
  sortByGenreButton.addEventListener("click", function() {
    sort("genre");
  });
  filterInput.addEventListener("input", function() {
    loadData("genre");
  });
}
function sort(sortValue) {
  if (sortValue === "AtoZ") {
    sortAtoZ();
  } else if (sortValue === "ZtoA") {
    sortZtoA();
  } else if (sortValue === "byParent") {
    sortByParent();
  } else if (sortValue === "genre") {
    sortByGenre();
  }
}
function sortAtoZ() {
  var membersInsideContainer, divsWithinContainer, i, switching, shouldSwitch;
  membersInsideContainer = document.getElementsByClassName("memberTitleHeadder");
  divsWithinContainer = document.getElementsByClassName("divImageMember");
  switching = true;

  while (switching) {
    switching = false;
    for (i = 0; i < (membersInsideContainer.length - 1); i++) {
      shouldSwitch = false;
      if (membersInsideContainer[i].innerHTML.toLowerCase() > membersInsideContainer[i + 1].innerHTML.toLowerCase()) {
        shouldSwitch = true;
        break;
      }
    }
    if (shouldSwitch) {
      divsWithinContainer[i].parentNode.insertBefore(divsWithinContainer[i + 1], divsWithinContainer[i]);
      switching = true;
    }
  }
}
function sortZtoA() {
  var membersInsideContainer, divsWithinContainer, i, switching, shouldSwitch;
  membersInsideContainer = document.getElementsByClassName("memberTitleHeadder");
  divsWithinContainer = document.getElementsByClassName("divImageMember");
  switching = true;

  while (switching) {
    switching = false;
    for (i = 0; i < (membersInsideContainer.length - 1); i++) {
      shouldSwitch = false;
      if (membersInsideContainer[i].innerHTML.toLowerCase() < membersInsideContainer[i + 1].innerHTML.toLowerCase()) {
        shouldSwitch = true;
        break;
      }
    }
    if (shouldSwitch) {
      divsWithinContainer[i].parentNode.insertBefore(divsWithinContainer[i + 1], divsWithinContainer[i]);
      switching = true;
    }
  }
}
function sortByParent() {

}
function sortByGenre() {

}


function main() {
  loadData("artist");
  setButtons();
}
main();
// artistContainer.innerHTML = "<p>test</p>";
// function mainFunction(callback) {
//   var someData = 'just data why are you waiting';
//   return new Promise(function(resolve, reject) {
//     setTimeout(function() {
//       resolve(someData);
//     }, 5000);
//   });
// }
//
// var promise = mainFunction(print);
// promise.then(print);
// // .then() <- om man vill bygga vidare
//
// function print(data) {
//   console.dir("data is " + data);
// }
