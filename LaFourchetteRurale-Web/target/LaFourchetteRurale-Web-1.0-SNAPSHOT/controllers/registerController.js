export default function registration(register) {
    return new Promise((resolve, reject) => {
        const backUrlRegistration = window.location.origin + "/api/rest/registration/new/user";

        const json2 = JSON.parse(localStorage.getItem("RegistrationInfos"));

        const requestOption2 = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(register)
        };

        fetch(backUrlRegistration, requestOption2)
        .then(response => response.text())
        .then(text => {
            if (text == -1) {
                document.getElementById("registrationMessage").textContent = "Problème en base de donnée";
                reject("Problème en base de données");
            } else if (text == -2) {
                document.getElementById("registrationMessage").textContent = "L'adresse mail est déjà utilisée !"
                reject("L'utilisateur existe déjà");
            } else if (text > 0) {
                document.getElementById("registrationMessage").textContent = ""
                let member = {};
                member.id = Number(text);
                localStorage.setItem("constructMember", JSON.stringify(member));
                setTimeout(() => {
                    // Résoudre la promesse avec un message de succès
                    resolve("Enregistrement réussi");
                }, 400);
            }
        })
        .catch(error => {
            console.error("Erreur lors de l'appel à l'API :", error);
            reject(error); // Rejeter la promesse en cas d'erreur
        });
    });
}
