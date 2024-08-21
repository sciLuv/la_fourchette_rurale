export default function getMembershipOfAMember() {
    return new Promise((resolve, reject) => {
        const backUrlMembership = window.location.origin + "/api/rest/membership/member/";
        const member = JSON.parse(localStorage.getItem("member"));
        const requestOption = {
            headers: { 'Content-Type': 'application/json' }
        };

        fetch(backUrlMembership + member.id, requestOption)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then(text => {
                if (text !== "") {
                    localStorage.setItem("membership", text);
                    resolve(text);
                } else {
                    reject("Empty membership data");
                }
            })
            .catch(error => {
                reject(error);
            });
    });
}
