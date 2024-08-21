export default function getDays() {
    console.log("DayController");
    const requestOption = {
        headers: { 'Content-Type': 'application/json' },
    };
    return new Promise((resolve, reject) => {
        fetch(window.location.origin + "/api/rest/day/all/days", requestOption)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then(text => {
                if (text !== "") {
                    localStorage.setItem("days", text);
                    resolve(); // Résoudre la promesse une fois les jours récupérés et stockés
                } else {
                    reject("Aucun jour trouvé."); // Rejeter la promesse en cas d'erreur
                }
            })
            .catch(error => {
                reject(error); // Rejeter la promesse en cas d'erreur
            });
    });
}
