/*
var app = {

    // Application Constructor
    initialize: function() {
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
    },

    onDeviceReady: function() {
        //Listar
        var db = firebase.firestore();
        var ag = db.collection("Estouque");

        ag.get()
        .then((querySnapshot) => {

            //Procurando eu exibindo todos os dados do seu banco, dentro de uma tabela

            querySnapshot.forEach((docRef) => {
                console.log(docRef.id, " => ", docRef.data());
                $("#TableData").append("<tr>");
                    $("#TableData").append("<td scope='col'>" + docRef.data().nome_do_campo + "</td>");
                    $("#TableData").append("<td scope='col'>" + docRef.data().nome_do_campo2 + "</td>");
                    $("#TableData").append("<td scope='col'><a href='" + cordova.file.applicationDirectory + "www/update.html?id=" + docRef.id + "'>Editar</a>&nbsp;|&nbsp;<a href='" + cordova.file.applicationDirectory + "www/delete.html?id=" + docRef.id + "'>Excluir</a></td>");
                $("#TableData").append("</tr>");
            });
        })
        .catch((error) => {
            console.log("Error getting documents: ", error);
        });
    }

};

app.initialize();
*/

var teste = {
    // Application Constructor
    initialize: function() {
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
    },

    onDeviceReady: function() { 
        const xhttp = new XMLHttpRequest();
        xhttp.onload = 
                function() {
                document.getElementById("TableData").innerHTML = this.responseText;
            }
        xhttp.open("GET", "http://localhost:40540/weatherforecast");
        xhttp.send(); 
    }
}

teste.initialize();