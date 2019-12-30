var memberContainer = document.getElementById("member_container");
var entityContainer = document.getElementById("viewEntityDivs");
var deleteButton = document.getElementById("delete");
var editButton = document.getElementById("edit");
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
            var data = JSON.parse(this.responseText);
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

}
// <h2 th:text="${artist.getEntityTitle()}" id="mainEntityHeader">ARTIST DISPLAYNAME</h2>
// <h3 th:text="${artist.getDescription()}" id="mainDescHeader">ARTIST DESCRIPTIONDISPLAY</h3>
// <h3 th:text="${album.getEntityTitle()}" class="entityHeadder">
function prepareEditScreen() {
    entityContainer.innerHTML = "";



    var submitButton = document.createElement("BUTTON");
    submitButton.innerHTML = "Submit changes";


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
    }
}

setButtonsAndPage();