var formButton = document.getElementById("submitButton");
var formHolder = document.getElementById("formHolderDiv");
var albumNameInput = document.getElementById("albumNameInput");
var descriptionInput = document.getElementById("descriptionInput");
var imgInput = document.getElementById("imageInputUrl");
var selectArtist = document.getElementById("selectArtist");
var firstTrack = document.getElementById("inputTrack0");
var addTrackButton = document.getElementById("addTrack");
var moreThanOneTrack = false;
var genreId =[];
var trackCounter = 1;

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
function getArtistsInsertToSelectArtist() {
    var ajax = getRequest();
    var urlString = "http://localhost:8080/api/artist/";

    ajax.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var data = JSON.parse(this.responseText);
            console.log(data);
            for (var i = 0; i < data.length; i++) {
                var artistOption = document.createElement("option");
                artistOption.text = data[i].name;
                selectArtist.add(artistOption);
                if (data[i].genre.genre === "Frenchcore") {
                    genreId.push(1);
                } else if (data[i].genre.genre === "Uptempo-Hardcore") {
                    genreId.push(2);
                } else if (data[i].genre.genre === "Hardcore") {
                    genreId.push(3);
                } else if (data[i].genre.genre === "Gabber") {
                    genreId.push(4);
                }
            }
        }
    };
    ajax.open("GET", urlString, true);
    ajax.send();
}
function postAlbum() {
    var ajax = getRequest();

    var album = {"entityName": albumNameInput.value, "description": descriptionInput.value,
        "artistId": (selectArtist.selectedIndex+1),
        "genreId": genreId[selectArtist.selectedIndex],
        "tracks" : [],
        "image":imgInput.value};

    var trackInputDataList = document.getElementsByClassName("inputTrackClass");
    for (var i = 0; i < trackInputDataList.length; i++) {
        album.tracks.push(trackInputDataList[i].value);
    }

    console.log(album);
    ajax.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var data = JSON.parse(this.responseText);
            console.log(data);
            formHolder.innerHTML = "<h2>Album: " + data.name + "</h2>" +
                "<h3>Has been added to our label.</h3>";
        }
    };
    ajax.open("POST", "http://localhost:8080/api/album/", true);
    ajax.setRequestHeader('Content-Type', 'application/json');
    ajax.send(JSON.stringify(album));
    console.log(selectArtist.selectedIndex);
    console.log(genreId[selectArtist.selectedIndex]);
}

function main() {
    getArtistsInsertToSelectArtist();
    formButton.addEventListener('click', function() {
        postAlbum();
    });
    selectArtist.addEventListener("change", function() {
        console.log(selectArtist.selectedIndex);
    });
    // if (!moreThanOneTrack) {
    //     firstTrack.addEventListener("input", function () {
    //         addTableRowForNewTrack();
    //     })
    // }
    addTrackButton.addEventListener("click", function() {
        trackCounter+=1;
        var td = document.createElement('tr');
        td.innerHTML = "<tr><td>" +
            "<label for='inputTrack"+trackCounter+"'>Track Title:</label>" +
            "<input type='text' name='track' id='inputTrack"+trackCounter+"' value='TRACK NAME' class='inputTrackClass'></td></tr>";
        document.getElementById("trackTableBody").append(td);
    })
}
console.log("add artist loaded");
main();