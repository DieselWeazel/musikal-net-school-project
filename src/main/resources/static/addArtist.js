var formButton = document.getElementById("submitButton");
var formHolder = document.getElementById("formHolderDiv");
var artistNameInput = document.getElementById("artistNameInput");
var descriptionInput = document.getElementById("descriptionInput");
var imgInput = document.getElementById("imageInputUrl");
var selectGenre = document.getElementById("selectGenre");
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

function postArtist() {
    console.log("postartist");
    var ajax = getRequest();

    var genreId = 0;
    if (selectGenre.value === "Frenchcore") {
        genreId = 1;
    } else if (selectGenre.value === "Uptempo-Hardcore") {
        genreId = 2;
    } else if (selectGenre.value === "Hardcore") {
        genreId = 3;
    } else if (selectGenre.value === "Gabber") {
        genreId= 4;
    }
    var artist = {"entityName": artistNameInput.value, "description": descriptionInput.value,
    "genreId":genreId, "image":imgInput.value};
    ajax.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var data = JSON.parse(this.responseText);
            console.log(data);
            formHolder.innerHTML = "<h2>Artist: " + data.entityName + "</h2>" +
                "<h3>Has been added to our label.</h3>";
        }
    };
    ajax.open("POST", "http://localhost:8080/api/artist/", true);
    ajax.setRequestHeader('Content-Type', 'application/json');
    ajax.send(JSON.stringify(artist));
}

function main() {
    formButton.addEventListener('click', function() {
        postArtist();
    });
}
console.log("add artist loaded");
main();