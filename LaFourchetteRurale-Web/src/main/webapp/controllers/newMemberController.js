export default function addANewMember() {
        const backUrlMemberShip = window.location.origin + "/api/rest/member/new/member";
        const json = JSON.parse(localStorage.getItem("constructMember"));
        const requestOption =  {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(json)
        };

        fetch(backUrlMemberShip, requestOption)
            .then(response => response.text())
            .then(text => {
                if(text !== "") {
                    console.log(text);
                } else {
                    
                }
            })
            .catch(error => {
                console.error("Error while adding new member:", error);
            });
}
