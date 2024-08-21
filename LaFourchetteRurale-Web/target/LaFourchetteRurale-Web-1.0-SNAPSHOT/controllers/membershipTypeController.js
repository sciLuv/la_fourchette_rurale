export default function getMembershipType() {
    return new Promise((resolve, reject) => {
        const requestOption = {
            headers: { 'Content-Type': 'application/json' },
        };
        fetch(window.location.origin + "/api/rest/membershipType/membershipTypes/valid", requestOption)
            .then(response => response.text())
            .then(text => {
                if (text !== "") {
                    localStorage.removeItem("membershipTypes");
                    localStorage.setItem("membershipTypes", text);
                    resolve(); // Résoudre la promesse si tout se passe bien
                } else {
                    reject('Membership types not found'); // Rejeter la promesse en cas d'erreur
                }
            })
            .catch(error => {
                reject(error); // Rejeter la promesse en cas d'erreur
            });
    });
}