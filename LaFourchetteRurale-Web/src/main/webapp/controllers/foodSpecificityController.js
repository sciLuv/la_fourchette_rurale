export default function getAllFoodSpecificity() {
    const backUrlFoodSpecificity = window.location.origin + "/api/rest/food/foods/valid";
    const requestOption2 = {
        headers: { 'Content-Type': 'application/json' },
    };

    return new Promise((resolve, reject) => {
        fetch(backUrlFoodSpecificity, requestOption2)
            .then(response => response.text())
            .then(text => {
                if (text !== "") {
                    localStorage.setItem("foodSpecificity", text);
                    resolve(); // Résoudre la promesse une fois les spécificités alimentaires récupérées et stockées
                } else {
                    reject("Aucune spécificité alimentaire trouvée."); // Rejeter la promesse en cas d'erreur
                }
            })
            .catch(error => {
                reject(error); // Rejeter la promesse en cas d'erreur
            });
    });
}
