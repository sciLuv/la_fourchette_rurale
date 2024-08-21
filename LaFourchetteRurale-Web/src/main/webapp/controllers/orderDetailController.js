export function getorderDetailFromMember() {
    return new Promise((resolve, reject) => {
        const backUrlOrderDetail = window.location.origin + "/api/rest/orderDetail/member/";
        const member = JSON.parse(localStorage.getItem("member"));
        const requestOption = {
            headers: { 'Content-Type': 'application/json' }
        };

        fetch(backUrlOrderDetail + member.id, requestOption)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then(text => {
                if (text !== "") {
                    localStorage.setItem("orderDetail", text);
                    resolve(text);
                } else {
                    reject("Empty order detail data");
                }
            })
            .catch(error => {
                reject(error);
            });
    });
}



export function setOrderDetailPaymentDate(){
    const backUrlOrderDetail = window.location.origin + "/api/rest/orderDetail/payment/date";

    const json2 = JSON.parse(localStorage.getItem("orderDetailPayment"))

    console.log(json2);
    const requestOption2 = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(json2)
    };


    fetch(backUrlOrderDetail , requestOption2)
    .then(response => response.text())
    .then(text => {
        if(text !== ""){
            /* localStorage.removeItem("orderDetailPayment") */
        } else {
            incorrectLoginDisplay()
        }
    })
}