import { emptyFieldDisplay, incorrectUsernameDisplay } from "../view/connectionView.js";

const backUrl = window.location.origin + "/api/rest/connection";

(() => addActionToConnectionButton())();

function addActionToConnectionButton() {
    const connectionButton = document.getElementById("connectionButton");
    connectionButton.addEventListener("click",authenticate);
}



function authenticate(event) {
        const username = this.form.loginInput.value;
        const password = event.target.form.passwordInput.value;
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({username: document.getElementById("loginInput").value, password:  document.getElementById("passwordInput").value })
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
                    window.location.href = "index.html";
                } else {
                    fetch(backUrl + "/authenticateDeliveryMan", requestOptions)
                        .then(response => response.text())
                        .then(text => {
                            if (text !== "") {
                                const json = JSON.parse(text);
                                const deliveryMan = {
                                    id: json.deliveryManId,
                                    firstname: json.firstname,
                                    lastname: json.lastname,
                                    password: json.password,
                                    mail: json.mail,
                                    role: json.role
                                }
                                localStorage.setItem("deliveryMan", JSON.stringify(deliveryMan));
                                localStorage.setItem("role", '"deliveryman"');
                                 localStorage.setItem("fourchetteHtml", "./connection.html");
                                 window.location.href = "index.html";
                            } else {
                                fetch(backUrl + "/authenticate/admin", requestOptions)
                                    .then(response => response.text())
                                    .then(text => {
                                        console.log(text);
                                            if (text == "true") {
                                                console.log("test1");
                                                localStorage.setItem("role",'"admin"');
                                                window.location.href = "index.html";
                                            } else {
                                                console.log("test2");
                                                incorrectUsernameDisplay();
                                            }
                                    });
                            }
                        });
                }
              })

}
/**
    function authenticateDeliveryMan(event) {
            let isDeliveryMan = false;
            const username = this.form.loginInput.value;
            const password = event.target.form.passwordInput.value;
            const requestOptions = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({username: document.getElementById("loginInput").value, password:  document.getElementById("passwordInput").value })
            };
  fetch(backUrl + "/authenticateDeliveryMan", requestOptions)
                                .then(response => response.text())
                                .then(text => {
                                    if (text !== "") {
                                        isDeliveryMan = true;
                                        const json = JSON.parse(text);
                                        const deliveryMan = {
                                            id: json.deliveryManId,
                                            firstname: json.firstname,
                                            lastname: json.lastname,
                                            password: json.password,
                                            mail: json.mail,
                                            role: json.role
                                        }

                                        localStorage.setItem("deliveryMan", JSON.stringify(deliveryMan));
                                        localStorage.setItem("fourchetteHtml", "./connection.html");
                                        window.location.href = "adhesion.html";
                                    } else {
                                        incorrectUsernameDisplay();
                                    }
                });

                return isDeliveryMan;
        }
        */