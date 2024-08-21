export default function getDiet() {
    console.log("dietController");
    const requestOption = {
        headers: { 'Content-Type': 'application/json' },
    };
    return new Promise((resolve, reject) => {
        fetch(window.location.origin + "/api/rest/diet/diets/valid", requestOption)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then(text => {
                if (text !== "") {
                    localStorage.removeItem("diets");
                    localStorage.setItem("diets", text);
                    resolve(); // Résoudre la promesse une fois les régimes récupérés et stockés
                } else {
                    reject("Aucun régime trouvé."); // Rejeter la promesse en cas d'erreur
                }
            })
            .catch(error => {
                reject(error); // Rejeter la promesse en cas d'erreur
            });
    });
}