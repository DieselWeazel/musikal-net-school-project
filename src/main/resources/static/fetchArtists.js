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
var selectGenre = document.getElementById("selectGenre");
var onArtistView;
var onAlbumView;
var onTrackView;
var onGenreView;

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

// Gets all the Data based on dataType
function loadData(dataType) {
  var ajax = getRequest();
  var urlString = "";
  if (dataType === "artist") {
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

// Filter Function, loaded from backend.
function loadFilteredData(filterValue) {
  var ajax = getRequest();
  var urlString = "";

  if (onArtistView) {
    urlString = "http://localhost:8080/api/artist/filter?filter=" + filterValue;
  }
  if (onAlbumView) {
    urlString = "http://localhost:8080/api/album/filter?filter=" + filterValue;
  }
  if (onTrackView) {
    urlString = "http://localhost:8080/api/track/filter?filter=" + filterValue;
  }

  console.log(urlString);
  ajax.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      console.log("searching");

      var data = JSON.parse(this.responseText);
      memberContainerHtml = "";
      if (onArtistView) {
        insertArtistMember(data);
      } else if (onAlbumView) {
        insertAlbumMember(data);
      } else if (onTrackView) {
        insertTrackMember(data);
      }
    }
  };
  ajax.open("POST", urlString, true);
  ajax.send();
}

// Filters by Genre via Backend
function loadByGenre(genre) {
  var ajax = getRequest();
  var urlString = "";

  if (onArtistView) {
    urlString = "http://localhost:8080/api/artist/genre?genre=" + genre;
  }
  if (onAlbumView) {
    urlString = "http://localhost:8080/api/album/genre?genre=" + genre;
  }
  if (onTrackView) {
    urlString = "http://localhost:8080/api/track/genre?genre=" + genre;
  }

  console.log(urlString);
  ajax.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      console.log("searching");

      var data = JSON.parse(this.responseText);
      memberContainerHtml = "";
      if (onArtistView) {
        insertArtistMember(data);
      } else if (onAlbumView) {
        insertAlbumMember(data);
      } else if (onTrackView) {
        insertTrackMember(data);
      }
    }
  };
  ajax.open("POST", urlString, true);
  ajax.send();
}

function insertArtistMember(data) {
  for (var i = 0; i < data.length; i++) {
    memberContainerHtml += "<div class='divImageMember'  onclick='location.href=\"view/artist/"
        + data[i].artistId + "\";'><img alt='" + data[i].name
        + "' height='370' width='370' src='" + data[i].image
        + "'/><h3 class ='memberTitleHeadder'>" + data[i].name
        + "</h3><h4 class ='memberGenreHeader'>" + data[i].genre.genre
        + "</h4><p>" + data[i].description + "</p></div>";
  }
  // html += "";
  memberContainer.innerHTML = memberContainerHtml;
  console.log(memberContainer);
}

function insertAlbumMember(data) {
  for (var i = 0; i < data.length; i++) {
    memberContainerHtml += "<div class='divImageMember'  onclick='location.href=\"view/album/"
        + data[i].id + "\";'><img alt='" + data[i].name
        + "' height='370' width='370' src='" + data[i].image
        + "'/><h3 class ='memberTitleHeadder'>" + data[i].name
        + "</h3><h4 class ='memberArtistHeader'>Artist: "
        + data[i].artist.artist + "</h4><h4 class ='memberGenreHeader'>"
        + data[i].genre.genre + "</h4><p>" + data[i].description + "</p></div>";
  }
  memberContainer.innerHTML = memberContainerHtml;
}

function insertGenreMember(data) {
  for (var i = 0; i < data.length; i++) {
    memberContainerHtml += "<div class='divImageMember'><h3 class ='memberTitleHeadder'>"
        + data[i].name + "</h3><p>" + data[i].description + "</p></div>";
  }
  memberContainer.innerHTML = memberContainerHtml;
}

function insertTrackMember(data) {
  for (var i = 0; i < data.length; i++) {
    memberContainerHtml += "<div class='divImageMember'><h3 class ='memberTitleHeadder'>"
        + data[i].name + "</h3><h4 class ='memberGenreHeader'>"
        + data[i].genre.genre + "</h4><h4 class ='memberArtistHeader'>Artist: "
        + data[i].artist.artist + "</h4><h4>Album: " + data[i].album.album
        + "</h4></div>";
  }
  memberContainer.innerHTML = memberContainerHtml;
}

function setButtons() {
  onArtistView = true;
  onAlbumView = false;
  onTrackView = false;
  onGenreView = false;
  sortByParentButton.innerHTML = "..";
  switchButton.addEventListener("click", function () {
    console.log("hey");
    if (memberContainer.className === "member_grid") {
      memberContainer.className = "member_list";
      return false;
    } else if (memberContainer.className === "member_list") {
      memberContainer.className = "member_grid";
    }
  });
  artistButton.addEventListener("click", function () {
    loadData("artist");
    sortByParentButton.innerHTML = "..";
    sortByParentButton.disabled = true;
    onArtistView = true;
    onAlbumView = false;
    onTrackView = false;
    onGenreView = false;
    filterInput.value = "";
  });
  albumButton.addEventListener("click", function () {
    loadData("album");
    sortByParentButton.innerHTML = "Sort By Artist";
    sortByParentButton.disabled = false;
    onArtistView = false;
    onAlbumView = true;
    onTrackView = false;
    onGenreView = false;
    filterInput.value = "";
  });
  genreButton.addEventListener("click", function () {
    loadData("genre");
    sortByParentButton.innerHTML = "..";
    sortByParentButton.disabled = true;
    onArtistView = false;
    onAlbumView = false;
    onTrackView = false;
    onGenreView = true;
    filterInput.value = "";
  });
  trackButton.addEventListener("click", function () {
    loadData("track");
    sortByParentButton.innerHTML = "Sort By Artist";
    sortByParentButton.disabled = false;
    onArtistView = false;
    onAlbumView = false;
    onTrackView = true;
    onGenreView = false;
    filterInput.value = "";
  });
  sortAtoZButton.addEventListener("click", function () {
    sort("AtoZ");
  });
  sortZtoAButton.addEventListener("click", function () {
    sort("ZtoA");
  });
  sortByParentButton.addEventListener("click", function () {
    sort("byParent");
  });
  sortByGenreButton.addEventListener("click", function () {
    sort("genre");
  });
  filterInput.addEventListener("input", function () {
    loadFilteredData(filterInput.value);
  });
  selectGenre.addEventListener("change", function () {
    if (selectGenre.value === "All") {
      console.log("all");
      if (onArtistView) {
        console.log("onArtistView");
        loadData("artist");
      }
      if (onAlbumView) {
        console.log("onAlbumView");
        loadData("album");
      }
      if (onTrackView) {
        console.log("onTrackView");
        loadData("track");
      }
      if (onGenreView) {
        console.log("onGenreView");
        loadData("genre");
      }
    } else if (selectGenre.value === "Frenchcore") {
      console.log("frenchcore");
      loadByGenre("Frenchcore");
    } else if (selectGenre.value === "Uptempo-Hardcore") {
      console.log("Uptempo");
      loadByGenre("Uptempo-Hardcore");
    } else if (selectGenre.value === "Hardcore") {
      console.log("Hardcore");
      loadByGenre("Hardcore");
    } else if (selectGenre.value === "Gabber") {
      console.log("Gabber");
      loadByGenre("Gabber");
    }
  })
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
  membersInsideContainer = document.getElementsByClassName(
      "memberTitleHeadder");
  divsWithinContainer = document.getElementsByClassName("divImageMember");
  switching = true;

  while (switching) {
    switching = false;
    for (i = 0; i < (membersInsideContainer.length - 1); i++) {
      shouldSwitch = false;
      if (membersInsideContainer[i].innerHTML.toLowerCase()
          > membersInsideContainer[i + 1].innerHTML.toLowerCase()) {
        shouldSwitch = true;
        break;
      }
    }
    if (shouldSwitch) {
      divsWithinContainer[i].parentNode.insertBefore(divsWithinContainer[i + 1],
          divsWithinContainer[i]);
      switching = true;
    }
  }
}

function sortZtoA() {
  var membersInsideContainer, divsWithinContainer, i, switching, shouldSwitch;
  membersInsideContainer = document.getElementsByClassName(
      "memberTitleHeadder");
  divsWithinContainer = document.getElementsByClassName("divImageMember");
  switching = true;

  while (switching) {
    switching = false;
    for (i = 0; i < (membersInsideContainer.length - 1); i++) {
      shouldSwitch = false;
      if (membersInsideContainer[i].innerHTML.toLowerCase()
          < membersInsideContainer[i + 1].innerHTML.toLowerCase()) {
        shouldSwitch = true;
        break;
      }
    }
    if (shouldSwitch) {
      divsWithinContainer[i].parentNode.insertBefore(divsWithinContainer[i + 1],
          divsWithinContainer[i]);
      switching = true;
    }
  }
}

//memberArtistHeader
function sortByParent() {
  var membersInsideContainer, divsWithinContainer, i, switching, shouldSwitch;
  membersInsideContainer = document.getElementsByClassName(
      "memberArtistHeader");
  divsWithinContainer = document.getElementsByClassName("divImageMember");
  switching = true;

  while (switching) {
    switching = false;
    for (i = 0; i < (membersInsideContainer.length - 1); i++) {
      shouldSwitch = false;
      if (membersInsideContainer[i].innerHTML.toLowerCase().slice(8)
          > membersInsideContainer[i + 1].innerHTML.toLowerCase().slice(8)) {
        shouldSwitch = true;
        break;
      }
    }
    if (shouldSwitch) {
      divsWithinContainer[i].parentNode.insertBefore(divsWithinContainer[i + 1],
          divsWithinContainer[i]);
      switching = true;
    }
  }
}

//memberGenreHeader
function sortByGenre() {
  var membersInsideContainer, divsWithinContainer, i, switching, shouldSwitch;
  membersInsideContainer = document.getElementsByClassName("memberGenreHeader");
  divsWithinContainer = document.getElementsByClassName("divImageMember");
  switching = true;

  while (switching) {
    switching = false;
    for (i = 0; i < (membersInsideContainer.length - 1); i++) {
      shouldSwitch = false;
      if (membersInsideContainer[i].innerHTML.toLowerCase()
          > membersInsideContainer[i + 1].innerHTML.toLowerCase()) {
        shouldSwitch = true;
        break;
      }
    }
    if (shouldSwitch) {
      divsWithinContainer[i].parentNode.insertBefore(divsWithinContainer[i + 1],
          divsWithinContainer[i]);
      switching = true;
    }
  }
}

function main() {
  loadData("artist");
  setButtons();
}

main();
