var artistContainer = document.getElementById("artist_container");

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

function loadData() {
  console.log("test?)");
  var ajax = getRequest();
  ajax.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      var data = this.responseText;
      console.log(data);
      // insertTable(data, schema);
      insertData(data);
    }
  };
  ajax.open("GET", "http://localhost:8080/api/artist/", true);
  ajax.send();
}

function insertData(data) {
  var html = "<div class='divMemberContainer'";
  for (var i = 0; i < data.length; i++) {
    html += insertMember(data[i]);
  }
  html += "</div>";
  artistContainer.innerHTML = html;
}

function insertMember(member) {
  var html = "";
  var name = member.name;
  var description = member.description;
  // var genre = member.genre[0];
  var imgLocation = member.image;
  // html += "<div class='divImageMember'><img alt=\"lol\" height=\"370\" width=\"370\" src=\"" + imgLocation + "\"/><p>"+ name +"</p><p>"+ genre +"</p>\"<p>"+ description +"</p></div>";
  html += "<div class='divImageMember'><img alt=\"+ member.name +\" height=\"370\" width=\"370\" src=\"" + imgLocation + "\"/><p>"+ name +"</p><p>"+ description +"</p></div>";

  return html;
}
function main() {
  console.log("FETCH ARTISTS!");
  loadData();
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
