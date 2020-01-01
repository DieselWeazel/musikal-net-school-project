var memberContainer = document.getElementById("member_container");
var entityContainer = document.getElementById("mainEntityDiv");
var deleteButton = document.getElementById("delete");
var editButton = document.getElementById("edit");
var buttonDiv = document.getElementById("divButtonEntityView");
var inputArtistName = document.createElement("INPUT");
var inputArtistDescName = document.createElement("INPUT");
var trackHeadders = document.getElementsByClassName("trackEntityHeader");
var trackInputElements;
var submitButton;
var data = "";
var albumData = "";
var onArtistView;
var onAlbumView;
var pathArray = window.location.pathname.split('/');

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

function deleteEntity() {
    var ajax = getRequest();
    var urlString = "";

    if (onArtistView) {
        console.log("deleting an artist");
        urlString = "http://localhost:8080/api/artist/" + pathArray[3];
    }
    if (onAlbumView) {
        urlString = "http://localhost:8080/api/album/" + pathArray[3];
    }

    ajax.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log("searching");
            // window.location.href = "../../artist";
            data = JSON.parse(this.responseText);
            console.log(data);
            if (onArtistView) {
                memberContainer.innerHTML = "<h2>" + data.name + " has been deleted</h2><p>All of his/her albums and tracks have been removed from our website.</p>";
            } else {
                memberContainer.innerHTML = "<h2>" + data.name + " has been deleted</h2><p>All tracks contained within the album has been removed.</p>";
            }
        }
    };
    ajax.open("DELETE", urlString, true);
    ajax.send();
}

function editEntity() {
    console.log("here");

    var ajax = getRequest();
    var urlString = "";

    if (onArtistView) {
        console.log("editing an artist");
        urlString = "http://localhost:8080/api/artist/" + pathArray[3];
        data = createArtist();
        console.log("here is artistdata");
        console.log(data);
        console.log(JSON.stringify(data));
    }
    if (onAlbumView) {
        urlString = "http://localhost:8080/api/album/" + pathArray[3];
        data = createAlbum();
        console.log("here is albumdata");
        // console.log(JSON.stringify(data));
    }


    ajax.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log("searching");
            window.location.href= pathArray[3];
        }
    };
    ajax.open("PUT", urlString, true);
    ajax.setRequestHeader('Content-Type', 'application/json');
    ajax.send(JSON.stringify(data));
}

function createArtist() {
    var artist = {"entityName" : inputArtistName.value, "description" : inputArtistDescName.value};
    return artist;
}

function createAlbum() {
    var album = {"entityName" : inputArtistName.value,
        "description" : inputArtistDescName.value,
        "artistId" : albumData.artistId,
        "genreId" : albumData.genreId,
        "tracks" : []
    }
    trackInputElements = document.getElementsByClassName("newInputTrack");
    for (var i = 0; i < trackInputElements.length; i++) {
        album.tracks.push(trackInputElements[i].value);
    }
    console.log(album);
    return album;
}

function prepareEditScreen() {
    // entityContainer.innerHTML = "";

    deleteButton.disabled = true;
    deleteButton.innerHTML = "..";

    submitButton = document.createElement("BUTTON");
    submitButton.innerHTML = "Submit changes";
    entityContainer.appendChild(submitButton);

    editButton.innerHTML = "cancel";

    console.log(data);
    editButton.addEventListener("click", function() {
        window.location.href= pathArray[3];
    })
    submitButton.addEventListener("click", function() {
        editEntity();
    })

    addEditFieldsToEntity()

    if (onAlbumView) {
        addEditAlbumFields();
    }
}
// trackDescHeader
// trackEntityHeader
function addEditAlbumFields() {
        for (var j = 0; j < trackHeadders.length; j++) {
            while (trackHeadders[j] !== undefined) {
                // console.log(trackHeadders[j]);
                var newInput = document.createElement("INPUT");
                newInput.className = "newInputTrack";
                newInput.value = trackHeadders[j].innerHTML;
                trackHeadders[j].replaceWith(newInput);
            }
        }

}

function addEditFieldsToEntity() {
    // inputArtistName = document.createElement("INPUT");
    var artistHeadderEntityTitle = document.getElementById("mainEntityHeader");
    inputArtistName.value = artistHeadderEntityTitle.innerHTML;
    var artistHeadderDescTitle = document.getElementById("mainDescHeader");
    inputArtistDescName.value = artistHeadderDescTitle.innerHTML;
    // inputArtistName.entityContainer.insertAfter(artistHeadderEntityTitle, inputArtistName);
    // entityContainer

    entityContainer.insertBefore(inputArtistName, artistHeadderDescTitle);
    entityContainer.insertBefore(inputArtistDescName, buttonDiv);
}
// http://localhost:8080/api/album/{id}
function setButtonsAndPage() {
    deleteButton.addEventListener("click", function() {
        deleteEntity()
    })
    editButton.addEventListener("click", function() {
        prepareEditScreen();
    })

    if (deleteButton.innerHTML === "Delete Artist") {
        onArtistView = true;
        onAlbumView = false;
        console.log("on delete artist");
    } else {
        onArtistView = false;
        onAlbumView = true;
        var ajax = getRequest();
        var urlString = "http://localhost:8080/api/album/" + pathArray[3];
        ajax.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                albumData = JSON.parse(this.responseText);
                // memberContainerHtml = "";
                console.log(albumData);
            }
        };
        ajax.open("GET", urlString, true);
        ajax.send();
    }
}

setButtonsAndPage();