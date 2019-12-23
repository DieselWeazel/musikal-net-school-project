// // $( document ).ready(function() {
// //   var api_url = 'https://localhost:8080/api/artist/'
// //   // var key = '5b578yg9yvi8sogirbvegoiufg9v9g579gviuiub8' // not real
// //
// //   $( ".content a" ).each(function( index, element ) {
// //
// //     $.ajax({
// //       url: api_url + "?key=" + key + " &q=" + $( this ).text(),
// //       url: + $( this ).text(),
// //       contentType: "application/json",
// //       dataType: 'json',
// //       success: function(result){
// //         console.log(result);
// //       }
// //     })
// //   });
// // });
//
// function connect() {
//   var api_url = 'https://localhost:8080/api/artist/'
//   // var key = '5b578yg9yvi8sogirbvegoiufg9v9g579gviuiub8' // not real
//
//   $( ".content a" ).each(function( index, element ) {
//
//     $.ajax({
//       url: api_url + "?key=" + key + " &q=" + $( this ).text(),
//       url: + $( this ).text(),
//       contentType: "application/json",
//       dataType: 'json',
//       success: function(result){
//         console.log(result);
//       }
//     })
//   });
// }
//
// $(function () {
//   // $("form").on('submit', function (e) {
//   //   e.preventDefault();
//   // });
//   // $("#send" ).click(function() {sendMessage();});
//   connect();
// });

// $(document).ready(function () {
//
//   $("#search-form").submit(function (event) {
//
//     //stop submit the form, we will post it manually.
//     event.preventDefault();
//
//     fire_ajax_submit();
//
//   });
//
// });
//
// function fire_ajax_submit() {
//
//   var search = {}
//   search["username"] = $("#username").val();
//
//   $("#btn-search").prop("disabled", true);
//
//   $.ajax({
//     type: "POST",
//     contentType: "application/json",
//     url: "/api/search",
//     data: JSON.stringify(search),
//     dataType: 'json',
//     cache: false,
//     timeout: 600000,
//     success: function (data) {
//
//       var json = "<h4>Ajax Response</h4><pre>"
//           + JSON.stringify(data, null, 4) + "</pre>";
//       $('#feedback').html(json);
//
//       console.log("SUCCESS : ", data);
//       $("#btn-search").prop("disabled", false);
//
//     },
//     error: function (e) {
//
//       var json = "<h4>Ajax Response</h4><pre>"
//           + e.responseText + "</pre>";
//       $('#feedback').html(json);
//
//       console.log("ERROR : ", e);
//       $("#btn-search").prop("disabled", false);
//
//     }
//   });
//
// }
//
// function load() {
//   $.ajax({
//     type: "GET",
//     contentType: "application/json",
//     url: "/api/artist/",
//     data: JSON,
//     dataType: 'json',
//     cache: false,
//     timeout: 600000,
//     success: function (data) {
//       console.log(data);
//     },
//     error: function (e) {
//       console.log("ERROR");
//     }
//   });
// }
function fetch() {
  $.ajax({
    url: 'http://localhost:8080/api/artist/'
  }).then(function (data) {
    console.log(data);
    $.each(data, function(index, item) {
      $("<p>"+ item.name +"</p></p><img alt=\"lol\" height=\"370\" width=\"370\" src=\"" + item.image + "\"/>").appendTo($("#test"));
    })
    // $("<img alt=\"lol\" height=\"370\" width=\"370\" src=\"" + data[0].image + "\"/>").appendTo($("#test"));
  });
}

function fetchAlbum() {
  $.ajax({
    url: 'http://localhost:8080/api/album/'
  }).then(function (data) {
    console.log(data);
    $.each(data, function(index, item) {
      $("<p>"+ item.name +"</p></p><img alt=\"lol\" height=\"370\" width=\"370\" src=\"" + item.image + "\"/>").appendTo($("#test"));
      var trackList = item.tracks;
      $.each(trackList, function(trackIndex, trackItem) {
        $("<p>" + trackItem.track +"</p>").appendTo($("#test"));
      })
    })

    // $("<img alt=\"lol\" height=\"370\" width=\"370\" src=\"" + data[0].image + "\"/>").appendTo($("#test"));
  });
}
//
$(function() {
  fetch();
  fetchAlbum();
});
// $(document).ready(function() {
//   $.ajax({
//     url: 'http://localhost:8080/api/artist/'
//   }).then(function (data) {
//     console.log(data);
//   });
// });
// $(function() {
//   // load();
// });