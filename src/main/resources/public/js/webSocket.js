/**
 * Created by cr7bo on 4/22/2017.
 */
//Copied from here: https://github.com/tipsy/spark-websocket/blob/master/src/main/resources/public/websocketDemo.js


//Establish the WebSocket connection and set up event handlers

    $(document).ready(function () {
        console.log("Hello from web socket");
        var projectId = $('#chatNav').attr('data-projectId');
        var userId = $('#chatNav').attr('data-userId');
        // console.log(userId + " is the user id");
        var webSocket = new WebSocket("ws://" + location.hostname + ":"
            + location.port + "/chatSocket?projectId=" + projectId +
            "&username=" + userId);
        webSocket.onmessage = function (msg) { updateChat(msg); };
        webSocket.onclose = function () { alert("WebSocket connection closed") };

        //Send message if "Send" is clicked
        // id("send").addEventListener("click", function () {
        //     sendMessage(id("message").value);
        // });

        //Send message if enter is pressed in the input field
        id("message").addEventListener("keypress", function (e) {
            if (e.keyCode === 13) { sendMessage(e.target.value); }
        });

        //Send a message if it's not empty, then clear the input field
        function sendMessage(message) {
            if (message !== "") {
                webSocket.send(message);
                id("message").value = "";
            }
        }

        //Update the chat-panel, and the list of connected users
        function updateChat(msg) {
            var data = JSON.parse(msg.data);
            insert("chat", data.userMessage);
             id("userlist").innerHTML = "";
             data.userlist.forEach(function (user) {
                 insert("userlist", "<li>" + user + "</li>");
             });
        }

        //Helper function for inserting HTML as the first child of an element
        function insert(targetId, message) {
            id(targetId).insertAdjacentHTML("afterbegin", message);
        }

        //Helper function for selecting element by id
        function id(id) {
            return document.getElementById(id);
        }
    });
