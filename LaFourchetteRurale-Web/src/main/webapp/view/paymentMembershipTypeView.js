import getMembershipType from "../controllers/membershipTypeController.js"
import getDays from "../controllers/dayController.js"
import getDiet from "../controllers/dietController.js"
import getAllFoodSpecificity from "../controllers/foodSpecificityController.js"
import getCitiesAndSave2 from "../controllers/getCitiesR.js"
import {getNowDate, getNextMonday, formattedDate, getNextDay, generateDateList, generateDateListAlternate, generateDateListAlternate2, calculerDatefacture, ajouterJours} from "../scripts/datesOperations.js"
import addANewMember from "../controllers/newMemberController.js"

getDays()
getDiet()
getAllFoodSpecificity()
getCitiesAndSave2()
getMembershipType()

let constructMember = JSON.parse(localStorage.getItem("constructMember"))
let member = JSON.parse(localStorage.getItem("member"))

let cities = JSON.parse(localStorage.getItem("cities"))
let diets = JSON.parse(localStorage.getItem("diets"))
let foods = JSON.parse(localStorage.getItem("foodSpecificity"))
let days = JSON.parse(localStorage.getItem("days"))
let membershipType = JSON.parse(localStorage.getItem("membershipTypes"))


function trouverMembershipParId(idRecherche) {
    return membershipType.find(membership => membership.membershipTypeId == idRecherche);
  }
console.log(trouverMembershipParId(1));
console.log(constructMember.membershipType);
console.log(trouverMembershipParId(constructMember.membershipType));

 ajouterJours(getNowDate(), trouverMembershipParId(constructMember.membershipType.duration))

document.getElementById("membershipTypeDisplaying").innerHTML = trouverMembershipParId(constructMember.membershipType).name
document.getElementById("membershipBeginning").innerHTML = getNowDate()
document.getElementById("membershipEnding").innerHTML = ajouterJours(getNowDate(), trouverMembershipParId(constructMember.membershipType).duration)
document.getElementById("membershipPrice").innerHTML = trouverMembershipParId(constructMember.membershipType).price


document.getElementById("nextPageButton").addEventListener('click', function(){
if  ((document.getElementById('numcarte').value != "")
    &&(document.getElementById('porteur').value != "")
    &&(document.getElementById('date').value != "")
    &&(document.getElementById("cryptogramme").value != "")){
        if (document.getElementById('savePaymentData').checked) {
            // Récupération des informations de paiement
            constructMember.numCarte= Number(document.getElementById('numcarte').value);
            constructMember.porteurCarte= document.getElementById('porteur').value;
            constructMember.dateCarte = document.getElementById('date').value;
        } else {
            // Utilisation de valeurs par défaut pour les informations de paiement
            constructMember.numCarte= Number(-1);
            constructMember.porteurCarte= "none";
            constructMember.dateCarte = document.getElementById('date').value;
        }
    
        constructMember.membershipBegin = getNowDate();
        constructMember.membershipEnd = ajouterJours(getNowDate(), trouverMembershipParId(constructMember.membershipType).duration);
        localStorage.setItem("constructMember", JSON.stringify(constructMember));
    
        // Appel de la fonction d'ajout d'un nouveau membre
        addANewMember()
    
    
                        localStorage.setItem("member", JSON.stringify({
                                                id: constructMember.id,
                                                firstname: constructMember.firstname,
                                                lastname: member.lastname,
                                                password: json.password,
                                                mail: member.mail,
                                                phone: constructMember.phone,
                                                idgender: constructMember.gender,
                                                birthdate: constructMember.birthdate,
                                                street: constructMember.street,
                                                deliveryInformation: constructMember.deliveryInformation,
                                                billAdress: constructMember.billAdress,
                                                idDiet: constructMember.diet,
                                                idTown: json.idTown,
                                                idTownBill: json.idTownBill
                        }));
                        localStorage.setItem("role", '"member"');
    
                                // Redirection vers la page "endMemberShip.html"
                    window.location.href = "endMemberShip.html"
    } else {
        document.getElementById("paiementMessage").innerHTML = "il manque des information de paiement."
    }


});


document.getElementById('prevPageButton').addEventListener("click", function(){
    window.location.href = "choix.html"
})


// Sélectionnez la case à cocher et le message à afficher
const saveCheckbox = document.getElementById('savePaymentData');
const saveMessage = document.getElementById('saveMessage');

// Écoutez les changements d'état de la case à cocher
saveCheckbox.addEventListener('change', function() {
    // Si la case à cocher est cochée, affichez le message, sinon cachez-le
    if (saveCheckbox.checked) {
        saveMessage.style.display = 'block';
    } else {
        saveMessage.style.display = 'none';
    }
});