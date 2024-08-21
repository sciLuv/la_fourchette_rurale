export default function fetchDeliveryDatesForMember(memberId) {
    return new Promise((resolve, reject) => {
        const url = window.location.origin + `/api/rest/AdhDeliveryDate/allDates/` + memberId;

        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                localStorage.setItem("dayOfDeliveryMember", JSON.stringify(data));
                resolve(data); // Résoudre la promesse avec les données récupérées
            })
            .catch(error => {
                reject(error); // Rejeter la promesse en cas d'erreur
                console.error('There was a problem with the fetch operation:', error);
            });
    });
}
