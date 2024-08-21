

export default function getAllDishType() {
    return new Promise((resolve, reject) => {
        const backUrlOrderDetail = window.location.origin + "/api/rest/dishType/all";
        const requestOption2 = {
            headers: { 'Content-Type': 'application/json' },
        };

        fetch(backUrlOrderDetail, requestOption2)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then(text => {
                if (text !== "") {
                    localStorage.setItem("dishType", text);
                    resolve(text);
                } else {
                    reject("Empty dish type data");
                }
            })
            .catch(error => {
                reject(error);
            });
    });
}