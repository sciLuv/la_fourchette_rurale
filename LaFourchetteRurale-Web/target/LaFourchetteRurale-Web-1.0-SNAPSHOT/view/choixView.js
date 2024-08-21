import getMembershipType from "../controllers/membershipTypeController.js"
import getDays from "../controllers/dayController.js"
import getDiet from "../controllers/dietController.js"
import getAllFoodSpecificity from "../controllers/foodSpecificityController.js"
import getCitiesAndSave2 from "../controllers/getCitiesR.js"

Promise.all([
    getDays(),
    getDiet(),
    getAllFoodSpecificity(),
    getCitiesAndSave2(),
    getMembershipType()
]).then(() => {
    // Toutes les fonctions asynchrones se sont exécutées avec succès
    let member = JSON.parse(localStorage.getItem("constructMember"));
    let cities = JSON.parse(localStorage.getItem("cities"));
    let diets = JSON.parse(localStorage.getItem("diets"));
    let foods = JSON.parse(localStorage.getItem("foodSpecificity"));
    let days = JSON.parse(localStorage.getItem("days"));
    let membershipType = JSON.parse(localStorage.getItem("membershipTypes"));

    let membershipTypeSelect = document.getElementById("type");
    for (let i = 0; i < membershipType.length; i++) {
        console.log(membershipType[i]);
        membershipTypeSelect.innerHTML +=
            `<option value="${membershipType[i].membershipTypeId}">${membershipType[i].name}</option>`;
    }

    document.getElementById("nextPageButton").addEventListener("click", function (event) {
        event.preventDefault();

        member.membershipType = Number(membershipTypeSelect.value);
        console.log("test");
        console.log(membershipTypeSelect.value);
        console.log(member);
        localStorage.setItem("constructMember", JSON.stringify(member));
        window.location.href = "paiementMembership.html";
        return false;
    });

    document.getElementById("prevPageButton").addEventListener("click", function () {
        window.location.href = "facturation.html";
    });
}).catch(error => {
    // Une ou plusieurs fonctions asynchrones ont échoué
    console.error('Une erreur s\'est produite lors de la récupération des données:', error);
});
