import {getorderDetailFromMember} from "../controllers/orderDetailController.js"
import getMembershipOfAMember from "../controllers/membershipController.js"


let member = JSON.parse(localStorage.getItem("member"));




Promise.all([getMembershipOfAMember(), getorderDetailFromMember()])
  .then(() => {
    const orderDetailInfos = JSON.parse(localStorage.getItem("orderDetail"))
    const membershipInfos = JSON.parse(localStorage.getItem("membership"))
    console.log(orderDetailInfos);
    const lastOrderDate = orderDetailInfos[orderDetailInfos.length-1].choiceValidationDate;
    console.log(lastOrderDate);

    let AlreadyOrderThisWeek = estDansSemaineActuelle(lastOrderDate);
    console.log(AlreadyOrderThisWeek);

  let weekComparison = beforeAfterOrSameWeek(membershipInfos[membershipInfos.length-1].expectedEndDate);

  if(AlreadyOrderThisWeek){
    document.getElementById('orderSection').classList.remove("orderSection");
  }  

  if(weekComparison == -1){
    document.getElementById("soonEndOfMembership").classList.remove("undisplayed")
  } else if(weekComparison == 0){
    document.getElementById("soonEndOfMembership").classList.remove("undisplayed")
  } else if(weekComparison > 0){
    document.getElementById("endOfMembership").classList.remove("undisplayed")
    document.getElementById('orderSection').classList.remove("orderSection");
  }
  })
  .catch(error => {
    console.error("Une erreur s'est produite :", error);
  });



function estDansSemaineActuelle(dateAComparer) {
    // Créer une nouvelle instance de Date pour la date actuelle
    let dateActuelle = new Date();
    let date = new Date(dateAComparer);
    // Obtenir le numéro de la semaine pour la date actuelle
    let semaineActuelle = dateActuelle.getWeek();
    console.log(semaineActuelle);
    // Obtenir le numéro de la semaine pour la date donnée
    let semaineDate = date.getWeek();
    console.log(semaineDate);
    // Comparer les numéros de semaine
    return semaineActuelle === semaineDate;
  }
  
  // Ajouter une méthode getWeek() à l'objet Date
  Date.prototype.getWeek = function() {
    var date = new Date(this.getTime());
    date.setHours(0, 0, 0, 0);
    date.setDate(date.getDate() + 3 - (date.getDay() + 6) % 7);
    var semaine1 = new Date(date.getFullYear(), 0, 4);
    return 1 + Math.round(((date - semaine1) / 86400000 - 3 + (semaine1.getDay() + 6) % 7) / 7);
  };
  

function beforeAfterOrSameWeek(date){
  let dateActuelle = new Date();
  let dateAComparer = new Date(date);
  let semaineActuelle = dateActuelle.getWeek();
  let semaineAComparer = dateAComparer.getWeek();
  let comparison = semaineActuelle - semaineAComparer
console.log(semaineActuelle);
console.log(semaineAComparer);
console.log(comparison);
  return comparison
}
