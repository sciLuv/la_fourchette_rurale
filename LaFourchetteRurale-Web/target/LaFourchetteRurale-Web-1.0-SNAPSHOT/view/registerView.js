import registration from "../controllers/registerController.js"

document.addEventListener("DOMContentLoaded", function() {
    const backUrl = window.location.origin + "/api/rest/connection";
    let register = {};
    let lastname = document.getElementById("nom");
    let firstname = document.getElementById("prenom");
    let mail = document.getElementById("email");
    let passwordInput = document.getElementById("password"); // Renommer la variable pour éviter les conflits
    let confirmationPassword = document.getElementById("confirmPassword");

    document.getElementById('btn').addEventListener('click', function(event) {
        event.preventDefault();

        if (confirmationPassword.value === passwordInput.value) { // Utiliser la variable renommée
            register.lastname = lastname.value;
            register.firstname = firstname.value;
            register.mail = mail.value;
            register.password = passwordInput.value; // Utiliser la variable renommée

            localStorage.setItem("registrationInfos", JSON.stringify(register));

            document.getElementById("registrationMessage").innerHTML=""
            
            // Appel de la fonction d'inscription
            registration(register)
                .then(function(message) {
                    // Mise à jour du stockage local
                    const username = mail.value;
                    const password = passwordInput.value; // Utiliser la variable renommée
                    const requestOptions = {
                        method: 'POST',
                        headers: { 'Content-Type': 'application/json' },
                        body: JSON.stringify({ username: username, password: password })
                    };
                    
                    fetch(backUrl + "/authenticate", requestOptions)
                        .then(response => response.text())
                        .then(text => {
                            if (text !== "") {
                                const json = JSON.parse(text);
                                const member = {
                                    id: json.id,
                                    firstname: json.firstname,
                                    lastname: json.lastname,
                                    password: json.password,
                                    mail: json.mail,
                                    phone: json.phone,
                                    idgender: json.idgender,
                                    birthdate: json.birthdate,
                                    street: json.street,
                                    deliveryInformation: json.deliveryInformation,
                                    billAdress: json.billAdress,
                                    creditCard: json.creditCard,
                                    creditCardDate: json.creditCardDate,
                                    creditCardMember: json.creditCardMember,
                                    idDiet: json.idDiet,
                                    idTown: json.idTown,
                                    idTownBill: json.idTownBill
                                }
                                localStorage.setItem("member", JSON.stringify(member));
                                localStorage.setItem("role", '"member"');
                                
                                // Redirection vers la page suivante après la mise à jour du stockage local
                                window.location.href = "adhesion.html";
                            } 
                        });
                })
                .catch(function(error) {
                    console.error("Erreur lors de l'inscription :", error);
                });
        } else {
            document.getElementById("registrationMessage").innerHTML= "Vos mot de passe et confirmation de mot de passe ne correspondent pas."
        }
        return false;
    });
});
